public class Student extends User implements Comparable {
    private Parent mother;
    private Parent father;

    public Student(String firstname, String lastName) {
        super(firstname, lastName);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getFather() {
        return father;
    }

    public Parent getMother() {
        return mother;
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        if (!this.getFirstName().equals(s.getFirstName())) {
            int lenght1 = this.getFirstName().length();
            int lenght2 = s.getFirstName().length();

            for (int i = 0; i < Math.min(lenght1,lenght2); i++) {
                if (this.getFirstName().charAt(i) > s.getFirstName().charAt(i))
                    return 1;
                if (this.getFirstName().charAt(i) < s.getFirstName().charAt(i))
                    return -1;

            }
            if(Math.min(lenght1,lenght2)==lenght1)
                return 1;
            return -1;
        }
        int lenght1 = this.getLastName().length();
        int lenght2 = s.getLastName().length();

        for (int i = 0; i < Math.min(lenght1,lenght2); i++) {
            if (this.getLastName().charAt(i) > s.getLastName().charAt(i))
                return 1;
            if (this.getLastName().charAt(i) < s.getLastName().charAt(i))
                return -1;

        }
        if(Math.min(lenght1,lenght2)==lenght1)
            return 1;
        return -1;
    }


}