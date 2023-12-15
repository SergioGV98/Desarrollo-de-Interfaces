package com.moronlu18.accounts.entity

data class User(val name: String, val surname: String, val email: String): Comparable<User>{

    override fun compareTo(other: User): Int {
        return name.compareTo(other.name)
    }

}
