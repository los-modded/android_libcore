/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.platform;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;

/**
 * This wraps native code that implements the INetworkSystem interface.
 * Address length was changed from long to int for performance reasons.
 */
final class OSNetworkSystem implements INetworkSystem {
    private static final OSNetworkSystem singleton = new OSNetworkSystem();

    public static OSNetworkSystem getOSNetworkSystem() {
        return singleton;
    }

    private OSNetworkSystem() {
    }

    public native void accept(FileDescriptor serverFd, SocketImpl newSocket, FileDescriptor clientFd) throws IOException;

    public native void close(FileDescriptor fd) throws IOException;

    public native int read(FileDescriptor fd, byte[] data, int offset, int count) throws IOException;
    public native int readDirect(FileDescriptor fd, int address, int count) throws IOException;
    public native int recv(FileDescriptor fd, DatagramPacket packet, byte[] data, int offset, int length, boolean peek, boolean connected) throws IOException;
    public native int recvDirect(FileDescriptor fd, DatagramPacket packet, int address, int offset, int length, boolean peek, boolean connected) throws IOException;

    public native boolean isConnected(FileDescriptor fd, int timeout) throws IOException;

    public native int select(FileDescriptor[] readFds, FileDescriptor[] writeFds, long timeout, int[] flags);

    public native int send(FileDescriptor fd, byte[] data, int offset, int length, int port, InetAddress inetAddress) throws IOException;
    public native int sendDirect(FileDescriptor fd, int address, int offset, int length, int port, InetAddress inetAddress) throws IOException;
    public native void sendUrgentData(FileDescriptor fd, byte value);
    public native int write(FileDescriptor fd, byte[] data, int offset, int count) throws IOException;
    public native int writeDirect(FileDescriptor fd, int address, int offset, int count) throws IOException;
}
