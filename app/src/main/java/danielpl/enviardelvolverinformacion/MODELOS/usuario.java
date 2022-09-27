package danielpl.enviardelvolverinformacion.MODELOS;

import java.io.Serializable;

public class usuario implements Serializable { //debemos implementar SERIALIZABLE en los objetos que vayamos a mover por nuestra aplicación

    private String email;
    private String password;

    public usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "email = " + email + "\n" +
                "password = " + password;
    }
}
