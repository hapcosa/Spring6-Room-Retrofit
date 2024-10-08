package com.example.spring6.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.spring6.component.fonoCard
import com.example.spring6.component.topBar
import com.example.spring6.viewModel.telefonoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavController, viewModel: telefonoViewModel= hiltViewModel()){
    Scaffold(
        topBar={topBar("Inicio", navController)},
        content = { ContentHomeView(it,navController, viewModel)},
        bottomBar = {}
    )

}

@Composable
fun ContentHomeView(paddingValues: PaddingValues, navController: NavController, viewModel: telefonoViewModel){
    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }
    val state by viewModel.fonos.collectAsState(listOf())

    Column(
        modifier = androidx.compose.ui.Modifier.padding(paddingValues)
    ) {

        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalItemSpacing = 6.dp,
            modifier = androidx.compose.ui.Modifier.padding(6.dp),
            content = {
                items(state)
                {
                    fonoCard(navController, it,viewModel)
                }
            }
        )

    }

}