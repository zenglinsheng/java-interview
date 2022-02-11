package coding.buffer;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BufferExamples {

    @Test
    public void gen() throws IOException {
        Random r = new Random();
        var fileName = "word";

        var bufferSize = 4 * 1024;
        var fout = new BufferedOutputStream(new FileOutputStream(fileName), bufferSize);

        var start = System.currentTimeMillis();

        for(int i = 0; i < 100000000; i ++) {
            for (int j = 0; j < 5; j++) {
                fout.write(97 + r.nextInt(5));
            }
            fout.write(' ');
        }
        fout.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void gen_nio() throws IOException {
        Random r = new Random();
        var fileName = "word";

        try(FileChannel fileChannel = new FileOutputStream(fileName).getChannel()) {
            ByteBuffer buffer;
            var start = System.currentTimeMillis();

            for (int i = 0; i < 250000;i ++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int b = 0;b < 400;b ++) {
                    for (int j = 0; j < 5; j++) {
                        stringBuilder.append((char)(97 + r.nextInt(5)));
                    }
                    stringBuilder.append(" ");
                }
                buffer = ByteBuffer.wrap(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));
                fileChannel.write(buffer);
            }

            System.out.println(System.currentTimeMillis() - start);
        }
    }

    @Test
    public void read_test() throws IOException {
        var fileName = "word";
        var in = new FileInputStream(fileName);

        var start = System.currentTimeMillis();

        int b;
//        var sb = new StringBuilder();
        while((b = in.read()) != -1) {
        }

        var end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
        in.close();
    }


    @Test
    public void read_test_withBuffer() throws IOException {
        var fileName = "word";
        var in = new BufferedInputStream(new FileInputStream(fileName));

        var start = System.currentTimeMillis();

        int b;
//        var sb = new StringBuilder();
        var bytes = new byte[1024*8];
        while((b = in.read(bytes)) != -1) {
        }

        var end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
        in.close();
    }

    @Test
    public void read_test_nio() throws IOException {

        // New I/O
        var fileName = "word";

        var channel = new FileInputStream(fileName).getChannel();
        var buff = ByteBuffer.allocate(1024*4);
        var start = System.currentTimeMillis();

        while(channel.read(buff) != -1) {
            buff.flip();
            // 读取数据
            //System.out.println(new String(buff.array()));
            buff.clear();
        }

        System.out.format("%dms\n", System.currentTimeMillis() - start);


    }

    @Test
    public void test_chinese(){
        String raw = "长坂桥头杀气生，横枪立马眼圆睁。一声好似轰雷震，独退曹家百万兵。";
        Charset charset = StandardCharsets.UTF_8;
        byte[] bytes = charset.encode(raw).array();
        byte[] bytes2 = Arrays.copyOfRange(bytes, 0, 11);

        ByteBuffer bbuf = ByteBuffer.allocate(12);
        CharBuffer cbuf = CharBuffer.allocate(12);

        bbuf.put(bytes2);
        bbuf.flip();

        charset.newDecoder().decode(bbuf, cbuf, true);
        cbuf.flip();

        char[] tmp = new char[cbuf.length()];
        if(cbuf.hasRemaining()) {
            cbuf.get(tmp);
            System.out.println("here:" + new String(tmp));
        }

        System.out.format("limit-pos=%d\n", bbuf.limit() - bbuf.position());

        //将bbuf中剩余的字节重新拷贝进bbuf
        byte[] bytesSurplus = Arrays.copyOfRange(bbuf.array(), bbuf.position(), bbuf.limit());
        bbuf.clear();
        bbuf.put(bytesSurplus);
    }

    @Test
    public void test_chinese02() {
        String raw = "长坂桥头杀气生，横枪立马眼圆睁。一声好似轰雷震，独退曹家百万兵。";
        Charset charset = Charset.forName("UTF-8");
        byte[] bytes = raw.getBytes();
        byte[] bytes2 = Arrays.copyOfRange(bytes, 0, 11);

        ByteBuffer bbuf = ByteBuffer.allocate(12);
        CharBuffer cbuf = CharBuffer.allocate(12);

        for (int i = 11; i <= bytes.length;) {
            bbuf.put(bytes2);
            bbuf.flip();

            charset.newDecoder().decode(bbuf, cbuf, true);
            cbuf.flip();

            char[] tmp = new char[cbuf.length()];
            if(cbuf.hasRemaining()) {
                cbuf.get(tmp);
                cbuf.clear();
                System.out.println(new String(tmp));
            }

            int differ = bbuf.limit() - bbuf.position();
            byte[] bytesSurplus = Arrays.copyOfRange(bbuf.array(), bbuf.position(), bbuf.limit());
            bbuf.clear();
            bbuf.put(bytesSurplus);
            if (i + 11 - differ == 101) {
                bytes2 = Arrays.copyOfRange(bytes, i, bytes.length);
                i = bytes.length;
            } else {
                bytes2 = Arrays.copyOfRange(bytes, i, i + 11 - differ);
                i = i + 11 - differ;
            }
        }
    }

    @Test
    public void test_async_read() throws IOException, ExecutionException, InterruptedException {
        var fileName = "word";
        var channel =
                AsynchronousFileChannel.open(Path.of(fileName), StandardOpenOption.READ);

        var buf = ByteBuffer.allocate(1024*8);
        Future<Integer> operation = channel.read(buf, 0);
        var numReads = operation.get();
        buf.flip();
        var chars = new String(buf.array());
        buf.clear();


    }
}
