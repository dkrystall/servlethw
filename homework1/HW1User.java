package cs320stu10.homework1;

/**
 * Created by dkrystall on 10/10/15.
 */
public class HW1User {
        private String name;
        private String email;
        private String password;
        public HW1User(String name,String email, String password){
            this.name = name;
            this.email = email;
            this.password = password;
        }
        public void setName(String name){
            name = this.name;
        }
        public String getName(){
            return this.name;
        }
        public void setEmail(String email){
            email = this.email;
        }
        public String getEmail(){
            return email;
        }
        public void setPassword(String password){
            password = this.password;
        }
        public String getPassword(){
            return password;
        }

}
