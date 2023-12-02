package com.auctioneer.mapper;

import com.auctioneer.entity.PlayerEntity;
import com.auctioneer.json.PlayerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper PLAYER_INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "userId", target = "playerId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "cityName", target = "city")
    @Mapping(source = "playingRole", target = "playingRole")
    @Mapping(source = "playerSkill", target = "playerSkill")
    @Mapping(target = "id", ignore = true)
    PlayerEntity responseToPlayerEntity(PlayerInfo.Player playerInfo);
}
