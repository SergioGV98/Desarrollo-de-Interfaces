package com.moronlu18.account.ui.userlist

import com.moronlu18.accounts.entity.User

sealed class UserListState() {

    data object NoDataError: UserListState()
    data class Success (val dataSet: ArrayList<User>) : UserListState()
    data class Loading (val value: Boolean): UserListState()

}