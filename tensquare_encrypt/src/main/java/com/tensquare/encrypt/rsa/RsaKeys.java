package com.tensquare.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 *
 * @author Administrator
 */
public class RsaKeys {

    //生成秘钥对的方法可以参考这篇帖子
    //https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

    //服务器公钥
    private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuzsWt3YWSSlhnY8vvOBo\n" +
            "jzsT3Eq2Jwp209YkwcnFnTiXGaxBE0xWYjXRjXUN76uxE4RZbkzOii4BX1gkCT01\n" +
            "cGP5CgVvRUsMAYzCpvjmOT708UATfDg7oWYbINKGxYDhSpr0Qc1GcJmucrlt4EbW\n" +
            "3GSGQp0Ccr3t5riwNQyDsNzDQEus2+ew2L6mu14DFkpvlOtuLaVeLFKfjpmyqI73\n" +
            "7WdXq/4kmFMaBkO6BiN/7bDdDULkO7xBisFEON1+ERdR7FzA2glWzOr5lOYXnH+5\n" +
            "P4Ok/G0hdLCHiSnP7SmzeynMQVg2FEw7E3Gwlg/DhGj5z4XnNeW7cKrcMtRjOzRb\n" +
            "6wIDAQAB";

    //服务器私钥(经过pkcs8格式处理)
    private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7Oxa3dhZJKWGd\n" +
            "jy+84GiPOxPcSrYnCnbT1iTBycWdOJcZrEETTFZiNdGNdQ3vq7EThFluTM6KLgFf\n" +
            "WCQJPTVwY/kKBW9FSwwBjMKm+OY5PvTxQBN8ODuhZhsg0obFgOFKmvRBzUZwma5y\n" +
            "uW3gRtbcZIZCnQJyve3muLA1DIOw3MNAS6zb57DYvqa7XgMWSm+U624tpV4sUp+O\n" +
            "mbKojvftZ1er/iSYUxoGQ7oGI3/tsN0NQuQ7vEGKwUQ43X4RF1HsXMDaCVbM6vmU\n" +
            "5hecf7k/g6T8bSF0sIeJKc/tKbN7KcxBWDYUTDsTcbCWD8OEaPnPhec15btwqtwy\n" +
            "1GM7NFvrAgMBAAECggEAC9MYhS+tCqPVaWkAn9IgaGNXxsLlLCQKCyjsG10N4PcQ\n" +
            "++sYFQruLbeua1uoLKnQPiZOq5uoM6viJE5hwD+gX1/A+7QKKfsqX/Ku3HK9v4HN\n" +
            "YGsmj+LRSUk0j2ccYd7Y26hbosg/GZ9rGA3D9bUjnrmujQhzfiGmo7qoTkFjE8lR\n" +
            "1M+z/brB0im8CGc8UGVp9d/BebEoTwHpjnB84d+l71RD/Xd1R32fUT9AMvHJ3/IE\n" +
            "pDAjXBmGoX73Xwtb+0UV1JKKKHT+XG5TtJpm5onioGKafqs+g6vPWcYndam8p226\n" +
            "zmeWHubm8dB4GjnxgvgObtVe574xPJ6bOGlcuE5jWQKBgQDhvfWdXjdxGKjyS3B0\n" +
            "F38IYrkcqkDyGE5ejW1SzxnOoDXWNBtVxbHDjPkBqWPHgilscmNwOxQPxp9LFAAL\n" +
            "/FVcPLuNdFceFxIUFJHW4DMyjEPl2NajZFYp4GEGbZSN30Y8HKZs29Dv0PSFpz4k\n" +
            "t8im+O+x+wSmc/HDYhhIFP1l1wKBgQDUU6ruj0UHGZw3zK/5B7fGPia7EI7p305g\n" +
            "xipAaC0d+/iVVsSj9qInWgWqzg9lL3qJ4593ND9fyB22tzhtJ+1faPkrS4YiG8vm\n" +
            "P3Drofa6o1bTCcAbSycm8nqnvTxPSTuP4ztu4299cCvHAEIE65QNYv/9H9gtPoq4\n" +
            "BJdrozVQDQKBgApHQ++uYofbv5WIqc4fY8FV/We4uMxNHwRfHCMxGtO1p73hEX2O\n" +
            "StHEkXHp7Ikg/BHcA3sQtZEiUg5xEq95Bcn1WfTO30iXdFNFvH40JyCeEllczikS\n" +
            "YHn3pXNNef4btG74Pvul5pWExh1vx/yUAabYntFTxJpvnQpwZPqjOgZxAoGBALyg\n" +
            "r+RLp3lNSTlRQKwuLReTM3lQloYy+kCDDBo38o0Gwqp5cSxw+VGYahx5/7dW3LgP\n" +
            "XiXaZsrCz5L5ZsmihQEUEvhhmgJHS3d7uOe89aKNZZ8KtH4K3/d7x3epaCDP4HhN\n" +
            "2QkESJHZyyp5ki0o1FioMN9BIR3gx///JD5dugkpAoGAREQcWMCOwDJhAdER7Mzo\n" +
            "7LOsqKp686sl6WPvo5c66S79/098UZ8mZIHW+SxcixrV0DU/p+4mtBrirKYcH1xt\n" +
            "EfKYXS8Y3/QU2qqGVr50vu0QAsH8G/8Kc+FDM7PkSqJt1wnnkHELANgNKihcq8dl\n" +
            "hlSsEAO6upspkxsD2AmkYgQ=";

    public static String getServerPubKey() {
        return serverPubKey;
    }

    public static String getServerPrvKeyPkcs8() {
        return serverPrvKeyPkcs8;
    }

}
