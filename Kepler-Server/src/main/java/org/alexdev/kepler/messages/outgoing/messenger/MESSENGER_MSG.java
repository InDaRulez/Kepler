package org.alexdev.kepler.messages.outgoing.messenger;

import org.alexdev.kepler.game.messenger.MessengerMessage;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;
import org.alexdev.kepler.util.DateUtil;
import org.alexdev.kepler.util.config.ServerConfiguration;

public class MESSENGER_MSG extends MessageComposer {
    private final MessengerMessage message;

    public MESSENGER_MSG(MessengerMessage message) {
        this.message = message;
    }

    @Override
    public void compose(NettyResponse response) {
        if (ServerConfiguration.getInteger("version") <= 14) {
            response.writeInt(1);
        }

        response.writeInt(this.message.getId());
        response.writeInt(this.message.getFromId());
        response.writeString(DateUtil.getDateAsString(this.message.getTimeSet()));
        response.writeString(this.message.getMessage());
    }

    @Override
    public short getHeader() {
        return 134; // "BF"
    }
}
