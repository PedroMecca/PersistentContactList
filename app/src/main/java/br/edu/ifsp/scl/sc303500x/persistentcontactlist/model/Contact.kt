package br.edu.ifsp.scl.sc303500x.persistentcontactlist.model

import java.io.Serializable

data class Contact(
    val id: Int = INVALID_CONTACT_ID,
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = ""
) : Serializable {
    companion object {
        const val INVALID_CONTACT_ID = -1
    }
}