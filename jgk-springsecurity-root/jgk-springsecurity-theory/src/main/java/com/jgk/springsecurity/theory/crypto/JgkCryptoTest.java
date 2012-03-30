package com.jgk.springsecurity.theory.crypto;

import org.junit.Assert;
import org.junit.Test;

public class JgkCryptoTest {

    @Test public void md5() {
        Assert.assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", JgkCrypto.getMd5AsText("password"));
        Assert.assertEquals("0ddbe36716977a9cfe7595b36e4409fe", JgkCrypto.getMd5AsText("jimispassword"));
    }
}
