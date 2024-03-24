    package com.ruralnative.handsy.data.repository

    import androidx.annotation.WorkerThread
    import com.ruralnative.handsy.data.dao.UserDao
    import com.ruralnative.handsy.data.entities.User
    import kotlinx.coroutines.flow.Flow
    import kotlinx.coroutines.flow.first
    import javax.inject.Inject
    import javax.inject.Singleton

    /**
     * Repository class for managing user data.
     *
     * This class provides methods to perform CRUD operations on user data.
     * It uses a [UserDao] to interact with the database.
     *
     * @property dao The Data Access Object for accessing user data.
     * @constructor Creates a new instance of UserRepository.
     */
    @Singleton
    @WorkerThread
    class UserRepository @Inject constructor(
        private val dao: UserDao
    ) {
        /**
         * Flow of all users in the database.
         */
        val allUsers: Flow<List<User>> = dao.selectAllUsers()

        /**
         * Retrieves a user by their ID.
         *
         * @param userID The ID of the user to retrieve.
         * @return A [Flow] of the user.
         */
        fun getUserByID(userID: Int): Flow<User> {
            return dao.selectUserById(userID)
        }

        /**
         * Checks if there are no users in the database.
         *
         * @return `true` if there are no users, `false` otherwise.
         */
        suspend fun isThereNoUser(): Boolean {
            val numberOfUsers: Int = dao.selectAllUsers().first().size
            return numberOfUsers == 0
        }

        /**
         * Inserts a new user into the database.
         *
         * @param user The user to insert.
         */
        suspend fun insertUser(user: User) {
            dao.insertUser(user)
        }

        /**
         * Inserts a new user with a specified ID and name into the database.
         *
         * @param id The ID of the new user.
         * @param name The name of the new user.
         */
        suspend fun insertNewUser(id: Int, name: String) {
            val user = User(
                id,
                name,
                0,
                1
            )
            dao.insertUser(user)
        }

        /**
         * Updates an existing user in the database.
         *
         * @param user The user to update.
         */
        suspend fun updateUser(user: User) {
            dao.updateUser(user)
        }

        /**
         * Updates the name of a user by their ID.
         *
         * @param userName The new name of the user.
         * @param userID The ID of the user to update.
         */
        suspend fun updateUserNameWithID(userName: String?, userID: Int) {
            dao.updateUserName(userName, userID)
        }

        /**
         * Updates the status of a user by their ID.
         *
         * @param boolValue The new status value.
         * @param userID The ID of the user to update.
         */
        suspend fun updateUserStatusWithID(boolValue: Int, userID: Int) {
            dao.updateUserStatus(boolValue, userID)
        }

        /**
         * Updates the progression level of a user by their ID.
         *
         * @param userLevel The new progression level.
         * @param userID The ID of the user to update.
         */
        suspend fun updateUserLevelWithID(userLevel: Int, userID: Int) {
            dao.updateUserProgressionLevel(userLevel, userID)
        }

        /**
         * Deletes a user from the database.
         *
         * @param user The user to delete.
         */
        suspend fun deleteUser(user: User) {
            dao.deleteUser(user)
        }
    }