package com.auctioneer.mapper;

import com.auctioneer.entity.Player;
import com.auctioneer.json.PlayerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper PLAYER_INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "cityName", target = "city")
    @Mapping(source = "playingRole", target = "playingRole")
    @Mapping(source = "playerSkill", target = "playerSkill")
    @Mapping(target = "id", ignore = true)
    Player responseToPlayerEntity(PlayerInfo.Player playerInfo);
}
