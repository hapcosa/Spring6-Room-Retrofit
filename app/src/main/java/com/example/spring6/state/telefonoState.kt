package com.example.spring6.state

import com.example.spring6.models.telefono

data class telefonoState(val telefonos : List<telefono> =emptyList(), val telefono : telefono = telefono())

