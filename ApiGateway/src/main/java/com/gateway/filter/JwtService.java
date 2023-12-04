package com.gateway.filter;

import org.springframework.stereotype.Service;

import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public static final String SECRET = "wcVoYHql7Bdz416aEkePjTqyRJLZBDSkK8s3EgZCVdDdmOM7beUKNxdI8JpatVnr2amtwIhxHWtk0pB7Vtx0Yluic0n7j3e8gJBoH97wqmro0HGg6iQruvgOqxn45HfV0OCIB86SlxGImejl7zHozN3lovxdh0";

    public void validateToken(final String token)
    {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
