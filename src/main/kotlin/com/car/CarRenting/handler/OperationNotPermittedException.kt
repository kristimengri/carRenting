package com.car.CarRenting.handler

class OperationNotPermittedException : RuntimeException{
    constructor() : super()
    constructor(message: String?) : super(message)
}