class student_database() {
    var userName: String? = null
    var faculty: String? = null
    var date: String? = null
    var time: String? = null

    constructor(userName: String?, faculty: String?, date: String?, time: String?) : this() {
        this.userName = userName
        this.faculty = faculty
        this.date = date
        this.time = time
    }
}
