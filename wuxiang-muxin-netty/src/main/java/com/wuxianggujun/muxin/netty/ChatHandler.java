package com.wuxianggujun.muxin.netty;

import com.wuxianggujun.muxin.enums.MsgActionEnum;
import com.wuxianggujun.muxin.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 处理消息的handler
 * TextWebSocketFrame：在netty中，用于为websocket专门处理文本的对象，frame是消息的载体。
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传输过来的消息
        String content = msg.text();
        Channel currentChannel = ctx.channel(); 
        //1.获取客户端发来的消息
        DataContent dataContent = JsonUtils.string2Obj(content, DataContent.class);
        Integer action = dataContent.getAction();
        //2.判断消息类型，根据不同的类型，处理不同的业务
        if (action == MsgActionEnum.CONNECT.type){
            //2.1 当websocket第一次open的时候，初始化channel，把用户的channel和userid关联起来
            String senderId = dataContent.getChatMsg().getSenderId();
            UserChannelRel.put(senderId,currentChannel);
        }else if (action == MsgActionEnum.CHAT.type){
            //2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态【未签收】
            
        }else if (action == MsgActionEnum.SIGNED.type){
            //2.3 签收消息，针对具体的消息进行签收，修改数据库中对应的消息状态【已签收】
            
            
        }else if (action == MsgActionEnum.KEEPALIVE.type){
            //2.4 心跳类型的消息
            
            
        }
    
       
       
      
       
        
        
    }

    /**
     * @param ctx
     * @throws Exception 当客户端连接服务端之后（打开连接）
     *                   获取客户端的channel，并且放到channel group中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handler removed，channel-group会自动移除对应客户端的channel
        clients.remove(ctx.channel());
        System.out.println("客户端断开，channel对应的长id为 ： " + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channel对应的短id为 ： " + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后关闭连接（关闭channel），随后从channelGroup中移除
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}
