package com.activemq;

public abstract class activeMQProducerAbstract {
	public final static int ACTIVEMQT_IMEOUT = 1000*10;
	public abstract void MsgProducerByQueue(String destination,String msg);
}
