package com.codinggyd.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

public class SFTP {
	 private Session session;//会话 
	    private Channel channel;//连接通道   
	    private ChannelSftp sftp;// sftp操作类   


	    public Session getSession() {
	        return session;
	    }
	    public void setSession(Session session) {
	        this.session = session;
	    }
	    public Channel getChannel() {
	        return channel;
	    }
	    public void setChannel(Channel channel) {
	        this.channel = channel;
	    }
	    public ChannelSftp getSftp() {
	        return sftp;
	    }
	    public void setSftp(ChannelSftp sftp) {
	        this.sftp = sftp;
	    }

}
