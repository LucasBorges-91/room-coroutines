package br.com.douglasmotta.naivagtioncomponentappmirror.data.repository

import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao.UserDAO
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.toUser
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.toUserEntity
import br.com.douglasmotta.naivagtioncomponentappmirror.data.model.User
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.RegistrationViewParams

class UserDbDataSource(
    private val userDAO : UserDAO
) : UserRepository {
    override fun createUser(registrationViewParams: RegistrationViewParams) {
        val userEntity = registrationViewParams.toUserEntity()
        userDAO.save( userEntity )
    }

    override fun getUser(id: Long): User {
        return userDAO.getUser( id ).toUser()
    }

    override fun login(username: String, password: String): Long {
        return userDAO.login( username, password )
    }
}