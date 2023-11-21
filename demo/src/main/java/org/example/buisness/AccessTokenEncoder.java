package org.example.buisness;

import org.example.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);

}
