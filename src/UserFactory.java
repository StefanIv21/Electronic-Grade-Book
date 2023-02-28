public class UserFactory {
    public  static User getUser(String username,String firstName,String lastName){
        if(username.equals("Parent"))
            return new Parent(firstName, lastName);
        if(username.equals("Student"))
            return new Student(firstName,lastName);
        if(username.equals("Assistant"))
            return new Assistant(firstName,lastName);
        if(username.equals("Teahcer"))
            return new Teacher(firstName,lastName);
        return null;
    }
}
