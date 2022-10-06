package zxk.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import zxk.entity.UmsUser;

public class JwtUitl {
    private static final String KEY = "wangyu";

    public static String create(UmsUser umsUser) {
        return JWT.create().withClaim("tokenId", umsUser.getId())
                .withClaim("tokenName", umsUser.getName())
                .sign(Algorithm.HMAC256(KEY));
    }
    public static UmsUser decode(String token) throws WyException {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(KEY))
                    .build().verify(token);
            Long tokenId = verify.getClaim("tokenId").asLong();
            String tokenName = verify.getClaim("tokenName").asString();
            UmsUser umsUser = new UmsUser();
            umsUser.setId(tokenId);
            umsUser.setName(tokenName);
            return umsUser;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WyException("非法请求");
        }
    }
}
