package pers.dreamer07.bean;

import java.util.List;

public class Lock {

    private Integer lockId;
    private String lockName;
    private List<Key> keyList;

    public Lock() {
    }

    public Lock(Integer lockId, String lockName) {
        this.lockId = lockId;
        this.lockName = lockName;
    }

    public Integer getLockId() {
        return lockId;
    }

    public void setLockId(Integer lockId) {
        this.lockId = lockId;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public List<Key> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Key> keyList) {
        this.keyList = keyList;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "lockId=" + lockId +
                ", lockName='" + lockName + '\'' +
                ", keyList=" + keyList +
                '}';
    }
}
