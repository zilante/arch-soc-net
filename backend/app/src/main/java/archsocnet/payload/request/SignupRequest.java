package archsocnet.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupRequest {

    @NotBlank
    @NotNull
    @Size(max = 40)
    private String username;

    @NotBlank
    @NotNull
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(max = 40)
    private String secondName;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
