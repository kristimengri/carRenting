package com.car.CarRenting.dto.request

import com.car.CarRenting.enums.DoorsEnum
import com.car.CarRenting.enums.FuelTypeEnum
import com.car.CarRenting.enums.TransmissionTypeEnum

class CarRequest {

    var model: String? = null
    var rentPrice: Double? = null
    var sellPrice: Double? = null
    var fuelType: FuelTypeEnum? = null
    var transmissionType: TransmissionTypeEnum? = null
    var doorType: DoorsEnum? = null
    var color: String? = null
    var description: String? = null

}