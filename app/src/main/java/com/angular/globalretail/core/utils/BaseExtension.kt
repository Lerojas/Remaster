package com.angular.globalretail.core.utils

fun Boolean?.isNullOrFalse(): Boolean {
    return ((this == null).or(this == false))
}

fun Boolean?.orFalse(): Boolean {
    return !this.isNullOrFalse()
}