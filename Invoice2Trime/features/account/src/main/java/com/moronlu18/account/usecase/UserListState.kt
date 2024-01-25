package com.moronlu18.account.usecase

import com.moronlu18.data.account.User

sealed class UserListState(){

    data object NoDataError: UserListState()
    data object Success: UserListState()
    data class Loading(val value:Boolean): UserListState()

}
