package com.gfc.library.event;

/**
 * @author admin
 * @date 2018/5/28
 * @function 功能
 */
public class EventMessage {
    private Enum<EventKeys> mKeysEnum;
    private Object mMessage;

    public EventMessage(Enum<EventKeys> keysEnum, Object message) {
        mKeysEnum = keysEnum;
        mMessage = message;
    }

    public Enum<EventKeys> getKeysEnum() {
        return mKeysEnum;
    }

    public void setKeysEnum(Enum<EventKeys> keysEnum) {
        mKeysEnum = keysEnum;
    }

    public Object getMessage() {
        return mMessage;
    }

    public void setMessage(Object message) {
        mMessage = message;
    }
}
