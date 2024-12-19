package com.angular.globalretail.core.domain

interface Mapper<out Destination, in Source> {
    fun executeMapping(type: Source?): Destination?
}