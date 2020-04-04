import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

Credentials c = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "seed-creds", "description", "user", "password")
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)
