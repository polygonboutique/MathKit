/**
 Copyright (c) 2013, Sergej Schefer
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Sergej Schefer.
 4. Neither the name of Sergej Schefer nor the
 names of its contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY SERGEJ SCHEFER ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL SERGEJ SCHEFER BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/* temp. class */
package mathkit.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BufferUtility {

    public static FloatBuffer packageBuffer(float[] data){
        ByteBuffer vbb = ByteBuffer.allocateDirect(data.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = vbb.asFloatBuffer();
        fb.put(data);
        fb.position(0);
        return fb;
    }

    public static FloatBuffer packageBuffer2(float[] data) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer packageBuffer3(float[] data){
        FloatBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
