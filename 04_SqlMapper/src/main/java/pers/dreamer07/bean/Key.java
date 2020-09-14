package pers.dreamer07.bean;

public class Key {

    private Integer keyId;
    private String keyName;
    private Lock lock; //可以开锁的对象

    public Key() {
    }

    public Key(Integer keyId, String keyName) {
        this.keyId = keyId;
        this.keyName = keyName;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "Key{" +
                "keyId=" + keyId +
                ", keyName='" + keyName + '\'' +
                ", lock=" + lock +
                '}';
    }
}
