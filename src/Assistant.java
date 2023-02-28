public class Assistant extends User implements Comparable,Element2 {
    public Assistant(String firstName,String lastName)
    {
        super(firstName,lastName);
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int compareTo(Object o) {
        Assistant s= (Assistant)o;
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