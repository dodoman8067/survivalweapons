package kro.dodoworld.survivalweapons.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import net.minecraft.EnumChatFormat;
import net.minecraft.core.BlockPosition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.network.syncher.DataWatcherSerializer;
import net.minecraft.world.EnumHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityPose;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.animal.EntityParrot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EnumGamemode;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreboardTeam;
import net.minecraft.world.scores.ScoreboardTeamBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftParrot;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_19_R1.util.CraftChatMessage;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/*
 * Credit goes to : https://gist.github.com/DanielTheDev/96862d0f79d24d8df029648ddc45c2c0
 */

public class NPC {

    private static AtomicInteger atomicInteger;

    private final int entityID;
    private final Location location;
    private GameProfile profile;
    private MetaData metadata = new MetaData();

    static {
        try {
            Field field = Entity.class.getDeclaredField("c");
            field.setAccessible(true);
            atomicInteger = (AtomicInteger) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public NPC(UUID uuid, Location location, String displayName) {
        this.entityID = atomicInteger.incrementAndGet();
        this.profile = new GameProfile(uuid, displayName);
        this.location = location;
    }

    public NPC(Location location, String displayName) {
        this(UUID.randomUUID(), location, displayName);
    }

    public void spawnNPC(Collection<? extends Player> receivers) {
        receivers.forEach(this::spawnNPC);
    }

    public void spawnNPC(Player receiver) {
        this.addToTabList(receiver);
        this.sendPacket(receiver, this.getEntitySpawnPacket());
        this.updateMetadata(receiver);
    }

    public void destroyNPC(Collection<? extends Player> receivers) {
        receivers.forEach(this::destroyNPC);
    }

    public void destroyNPC(Player receiver) {
        this.sendPacket(receiver, this.getPlayerInfoPacket(PlayerInfo.REMOVE_PLAYER));
        this.sendPacket(receiver, this.getEntityDestroyPacket());
    }

    public void reloadNPC(Collection<? extends Player> receivers) {
        receivers.forEach(this::reloadNPC);
    }

    public void reloadNPC(Player receiver) {
        this.destroyNPC(receiver);
        this.spawnNPC(receiver);
    }

    public void teleportNPC(Collection<? extends Player> receivers, Location location, boolean onGround) {
        receivers.forEach(receiver -> this.teleportNPC(receiver, location, onGround));
    }

    public void teleportNPC(Player receiver, Location location, boolean onGround) {
        this.location.setX(location.getX());
        this.location.setY(location.getY());
        this.location.setZ(location.getZ());
        this.location.setPitch(location.getPitch());
        this.location.setYaw(location.getYaw());
        this.rotateHead(receiver, location.getPitch(), location.getYaw(), true);
        this.sendPacket(receiver, this.getEntityTeleportPacket(onGround));
    }

    public void updateMetadata(Collection<? extends Player> receivers) {
        receivers.forEach(this::updateMetadata);
    }

    public void updateMetadata(Player receiver) {
        this.sendPacket(receiver, this.getEntityMetadataPacket());
    }

    public void updateGameMode(Collection<? extends Player> receivers) {
        receivers.forEach(this::updateGameMode);
    }

    public void updateGameMode(Player receiver) {
        this.sendPacket(receiver, this.getPlayerInfoPacket(PlayerInfo.UPDATE_GAME_MODE));
    }

    public void setPing(Collection<? extends Player> receivers, Ping ping) {
        receivers.forEach(receiver -> this.setPing(receiver, ping));
    }

    public void setPing(Player receiver, Ping ping) {
        this.sendPacket(receiver, this.getUpdatePlayerInfoPacket(PlayerInfo.UPDATE_LATENCY, ping));
    }

    public void setGameMode(Collection<? extends Player> receivers, GameMode gameMode) {
        receivers.forEach(receiver -> this.setGameMode(receiver, gameMode));
    }

    public void setGameMode(Player receiver, GameMode gameMode) {
        this.sendPacket(receiver, this.getUpdatePlayerInfoPacket(PlayerInfo.UPDATE_GAME_MODE, gameMode));
    }

    public void setTabListName(Collection<? extends Player> receivers, String displayName) {
        receivers.forEach(receiver -> this.setTabListName(receiver, displayName));
    }

    public void setTabListName(Player receiver, String displayName) {
        this.sendPacket(receiver, this.getUpdatePlayerInfoPacket(PlayerInfo.UPDATE_DISPLAY_NAME, displayName));
    }

    public void removeFromTabList(Collection<? extends Player> receivers) {
        receivers.forEach(this::removeFromTabList);
    }

    public void removeFromTabList(Player receiver) {
        this.sendPacket(receiver, this.getPlayerInfoPacket(PlayerInfo.REMOVE_PLAYER));
    }

    public void addToTabList(Collection<? extends Player> receivers) {
        receivers.forEach(this::addToTabList);
    }

    public void addToTabList(Player receiver) {
        this.sendPacket(receiver, this.getPlayerInfoPacket(PlayerInfo.ADD_PLAYER));
    }

    public void playAnimation(Collection<? extends Player> receivers, Animation animation) {
        receivers.forEach(receiver -> this.playAnimation(receiver, animation));
    }

    public void playAnimation(Player receiver, Animation animation) {
        this.sendPacket(receiver, this.getEntityAnimationPacket(animation));
    }

    public void lookAtPlayer(Collection<? extends Player> receivers, Player target) {
        receivers.forEach(receiver -> this.lookAtPlayer(receiver, target));
    }

    public void lookAtPlayer(Player receiver, Player target) {
        this.lookAtPoint(receiver, target.getEyeLocation());
    }

    public void lookAtPoint(Collection<? extends Player> receivers, Location location) {
        receivers.forEach(receiver -> this.lookAtPoint(receiver, location));
    }

    public void lookAtPoint(Player receiver, Location location) {
        Location eyeLocation = this.getEyeLocation();
        float yaw = (float) Math.toDegrees(Math.atan2(location.getZ() - eyeLocation.getZ(), location.getX() - eyeLocation.getX())) - 90;
        yaw = (float) (yaw + Math.ceil(-yaw / 360) * 360);
        float deltaXZ = (float) Math.sqrt(Math.pow(eyeLocation.getX() - location.getX(), 2) + Math.pow(eyeLocation.getZ() - location.getZ(), 2));
        float pitch = (float) Math.toDegrees(Math.atan2(deltaXZ, location.getY() - eyeLocation.getY())) - 90;
        pitch = (float) (pitch + Math.ceil(-pitch / 360) * 360);
        this.rotateHead(receiver, pitch, yaw, true);
    }

    public void rotateHead(Collection<? extends Player> receivers, float pitch, float yaw, boolean body) {
        receivers.forEach(receiver -> this.rotateHead(receiver, pitch, yaw, body));
    }

    public void rotateHead(Player receiver, float pitch, float yaw, boolean body) {
        this.location.setPitch(pitch);
        this.location.setYaw(yaw);
        System.out.println(yaw);
        if(body) this.sendPacket(receiver, this.getEntityLookPacket());
        this.sendPacket(receiver, this.getEntityHeadRotatePacket());
    }

    public void setEquipment(Collection<? extends Player> receivers, ItemSlot slot, org.bukkit.inventory.ItemStack itemStack) {
        receivers.forEach(receiver -> this.setEquipment(receiver, slot, itemStack));
    }

    public void setEquipment(Player receiver, ItemSlot slot, org.bukkit.inventory.ItemStack itemStack) {
        this.sendPacket(receiver, this.getEntityEquipmentPacket(slot.getNMSItemSlot(), CraftItemStack.asNMSCopy(itemStack)));
    }

    public void setPassenger(Collection<? extends Player> receivers, int... entityIDs) {
        receivers.forEach(receiver -> this.setPassenger(receiver, entityIDs));
    }

    public void setPassenger(Player receiver, int... entityIDs) {
        this.sendPacket(receiver, getEntityAttachPacket(entityIDs));
    }

    public void setLeash(Player receiver, org.bukkit.entity.Entity entity) {
        this.sendPacket(receiver, getAttachEntityPacket(entity));
    }

    public void setLeash(Collection<? extends Player> receivers, org.bukkit.entity.Entity entity) {
        receivers.forEach(p->this.setLeash(p, entity));
    }

    public void moveRelative(Player receiver, double relX, double relY, double relZ, boolean onGround) {
        this.sendPacket(receiver, getEntityMovePacket(relX, relY, relZ, onGround));
    }

    public void moveRelative(Collection<? extends Player> receivers, double relX, double relY, double relZ, boolean onGround) {
        receivers.forEach(p->this.moveRelative(p, relX, relY, relZ, onGround));
    }

    public BukkitCompletable<NPC> setSkinByUsername(Plugin plugin, String username) {
        return BukkitCompletable.supplyASync(plugin, ()->{
            this.setSkin(NPC.SkinTextures.getByUsername(plugin, username).getSync());
            return NPC.this;
        });
    }

    public BukkitCompletable<NPC> setSkinByUUID(Plugin plugin, UUID uuid) {
        return BukkitCompletable.supplyASync(plugin, ()->{
            this.setSkin(NPC.SkinTextures.getByUUID(plugin, uuid).getSync());
            return NPC.this;
        });
    }

    private void sendPacket(Player receiver, Packet<?> packet) {
        ((CraftPlayer) (receiver)).getHandle().b.a(packet);
    }

    public void setTeamFeatures(Player receiver, TeamFeatures teamFeatures) {
        ScoreboardTeam team = new ScoreboardTeam(new Scoreboard(), this.profile.getName());
        team.a(teamFeatures.nameTagVisible() ? ScoreboardTeamBase.EnumNameTagVisibility.a : ScoreboardTeamBase.EnumNameTagVisibility.b);
        team.a(teamFeatures.glowColor().getNMSColor());
        team.a(teamFeatures.collision() ? ScoreboardTeamBase.EnumTeamPush.a : ScoreboardTeamBase.EnumTeamPush.b);
        PacketPlayOutScoreboardTeam createPacket = PacketPlayOutScoreboardTeam.a(team, true);
        PacketPlayOutScoreboardTeam joinPacket = PacketPlayOutScoreboardTeam.a(team, this.profile.getName(), PacketPlayOutScoreboardTeam.a.a);
        this.sendPacket(receiver, createPacket);
        this.sendPacket(receiver, joinPacket);
    }

    public NBTTagCompound createParrot(Consumer<Parrot> callback, World world) {
        EntityParrot entityParrot = new EntityParrot(EntityTypes.ao, ((CraftWorld) world).getHandle());
        CraftParrot parrot = new CraftParrot((CraftServer) Bukkit.getServer(), entityParrot);
        callback.accept(parrot);
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        entityParrot.d(nbtTagCompound);
        return nbtTagCompound;
    }

    public void setParrotLeftShoulder(Consumer<Parrot> callback, World world) {
        this.metadata.setLeftShoulder(this.createParrot(callback, world));
    }

    public void setParrotRightShoulder(Consumer<Parrot> callback, World world) {
        this.metadata.setRightShoulder(this.createParrot(callback, world));
    }

    public int getEntityID() {
        return entityID;
    }

    public GameProfile getProfile() {
        return profile;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public Location getLocation() {
        return location;
    }

    public Location getEyeLocation() {
        return this.location.clone().add(0, EntityTypes.bn.m().b * 0.85F, 0);
    }

    public MetaData getMetaData() {
        return this.metadata;
    }

    public void setMetaData(MetaData metaData) {
        this.metadata = metaData;
    }

    public void setSkin(SkinTextures skinTextures) {
        this.profile.getProperties().put("textures", new Property("textures", skinTextures.getTexture(), skinTextures.getSignature()));
    }

    public void setDisplayName(String displayName) {
        GameProfile swapProfile = new GameProfile(this.profile.getId(), displayName);
        swapProfile.getProperties().putAll(this.profile.getProperties());
        this.profile = swapProfile;
    }

    public void registerListener(NPCListener listener) {
        if (EventManager.isInitialized()) {
            EventManager.INSTANCE.listenNPC(this, listener);
        }
    }

    public void unregisterListener() {
        if (EventManager.isInitialized()) {
            EventManager.INSTANCE.unlistenNPC(this);
        }
    }

    private PacketPlayOutMount getEntityAttachPacket(int[] entityIDs) {
        return this.createDataSerializer(data -> {
            data.d(this.entityID);
            data.a(entityIDs);
            return new PacketPlayOutMount(data);
        });
    }

    private PacketPlayOutEntity.PacketPlayOutEntityLook getEntityLookPacket() {
        return new PacketPlayOutEntity.PacketPlayOutEntityLook(this.entityID, (byte) ((int) (this.location.getYaw() * 256.0F / 360.0F)), (byte) ((int) (this.location.getPitch() * 256.0F / 360.0F)), true);
    }

    private PacketPlayOutEntityHeadRotation getEntityHeadRotatePacket() {
        return this.createDataSerializer(data -> {
            data.d(this.entityID);
            data.writeByte((byte) ((int) (this.location.getYaw() * 256.0F / 360.0F)));
            return new PacketPlayOutEntityHeadRotation(data);
        });
    }

    private PacketPlayOutEntityTeleport getEntityTeleportPacket(boolean onGround) {
        return this.createDataSerializer(data -> {
            data.d(this.entityID);
            data.writeDouble(this.location.getX());
            data.writeDouble(this.location.getY());
            data.writeDouble(this.location.getZ());
            data.writeByte((byte) ((int) (this.location.getYaw() * 256.0F / 360.0F)));
            data.writeByte((byte) ((int) (this.location.getPitch() * 256.0F / 360.0F)));
            data.writeBoolean(onGround);
            return new PacketPlayOutEntityTeleport(data);
        });
    }

    private PacketPlayOutEntity.PacketPlayOutRelEntityMove getEntityMovePacket(double x, double y, double z, boolean onGround) {
        return new PacketPlayOutEntity.PacketPlayOutRelEntityMove(this.entityID, (short)(x * 4096), (short)(y * 4096), (short)(z * 4096), onGround);
    }

    private PacketPlayOutAttachEntity getAttachEntityPacket(org.bukkit.entity.Entity entity) {
        return createDataSerializer(data -> {
            data.writeInt(entity.getEntityId());
            data.writeInt(this.entityID);
            return new PacketPlayOutAttachEntity(data);
        });
    }

    private PacketPlayOutEntityEquipment getEntityEquipmentPacket(EnumItemSlot slot, ItemStack itemStack) {
        return new PacketPlayOutEntityEquipment(this.entityID, Arrays.asList(new Pair<>(slot, itemStack)));
    }

    private PacketPlayOutAnimation getEntityAnimationPacket(Animation animation) {
        return this.createDataSerializer((data) -> {
            data.d(this.entityID);
            data.writeByte((byte) animation.getType());
            return new PacketPlayOutAnimation(data);
        });
    }

    private PacketPlayOutEntityDestroy getEntityDestroyPacket() {
        return new PacketPlayOutEntityDestroy(this.entityID);
    }

    private PacketPlayOutEntityMetadata getEntityMetadataPacket() {
        return this.createDataSerializer((data) -> {
            data.d(this.entityID);
            DataWatcher.a(this.metadata.getList(), data);
            return new PacketPlayOutEntityMetadata(data);
        });
    }

    private PacketPlayOutNamedEntitySpawn getEntitySpawnPacket() {
        return this.createDataSerializer((data) -> {
            data.d(this.entityID);
            data.a(this.profile.getId());
            data.writeDouble(this.location.getX());
            data.writeDouble(this.location.getY());
            data.writeDouble(this.location.getZ());
            data.writeByte((byte) ((int) (this.location.getYaw() * 256.0F / 360.0F)));
            data.writeByte((byte) ((int) (this.location.getPitch() * 256.0F / 360.0F)));
            return new PacketPlayOutNamedEntitySpawn(data);
        });
    }

    private PacketPlayOutPlayerInfo getUpdatePlayerInfoPacket(PlayerInfo playerInfo, Object obj) {
        return this.createDataSerializer((data) -> {
            PacketPlayOutPlayerInfo.EnumPlayerInfoAction action = playerInfo.getNMSAction();
            PacketPlayOutPlayerInfo.PlayerInfoData playerInfoData;

            net.minecraft.world.entity.player.ProfilePublicKey.a playerPublicKey = null;

            if (playerInfo == PlayerInfo.UPDATE_LATENCY) {
                playerInfoData = new PacketPlayOutPlayerInfo.PlayerInfoData(this.profile, ((Ping) obj).getMilliseconds(), null, null, playerPublicKey);
            } else if (playerInfo == PlayerInfo.UPDATE_GAME_MODE) {
                playerInfoData = new PacketPlayOutPlayerInfo.PlayerInfoData(this.profile, 0, ((GameMode) obj).getNMSGameMode(), null, playerPublicKey);
            } else if (playerInfo == PlayerInfo.UPDATE_DISPLAY_NAME) {
                playerInfoData = new PacketPlayOutPlayerInfo.PlayerInfoData(this.profile, 0, null, CraftChatMessage.fromString(((String) obj))[0], playerPublicKey);
            } else {
                throw new NullPointerException();
            }

            List<PacketPlayOutPlayerInfo.PlayerInfoData> list = Arrays.asList(playerInfoData);
            Method method = playerInfo.getNMSAction().getDeclaringClass().getDeclaredMethod("a", PacketDataSerializer.class, PacketPlayOutPlayerInfo.PlayerInfoData.class);
            method.setAccessible(true);
            data.a(playerInfo.getNMSAction());
            data.a(list, (PacketDataSerializer.b<PacketPlayOutPlayerInfo.PlayerInfoData>) (a, b) -> this.unsafe(() -> method.invoke(action, a, b)));
            return new PacketPlayOutPlayerInfo(data);
        });
    }

    private PacketPlayOutPlayerInfo getPlayerInfoPacket(PlayerInfo playerInfo) {
        return this.createDataSerializer((data) -> {
            PacketPlayOutPlayerInfo.EnumPlayerInfoAction action = playerInfo.getNMSAction();
            net.minecraft.world.entity.player.ProfilePublicKey.a playerPublicKey = null;
            PacketPlayOutPlayerInfo.PlayerInfoData playerInfoData = new PacketPlayOutPlayerInfo.PlayerInfoData(this.profile, Ping.FIVE_BARS.getMilliseconds(), GameMode.CREATIVE.getNMSGameMode(), CraftChatMessage.fromString(this.getProfile().getName())[0], playerPublicKey);
            List<PacketPlayOutPlayerInfo.PlayerInfoData> list = Arrays.asList(playerInfoData);
            data.a(playerInfo.getNMSAction());
            Method method = playerInfo.getNMSAction().getDeclaringClass().getDeclaredMethod("a", PacketDataSerializer.class, PacketPlayOutPlayerInfo.PlayerInfoData.class);
            method.setAccessible(true);
            data.a(list, (PacketDataSerializer.b<PacketPlayOutPlayerInfo.PlayerInfoData>) (a, b) -> this.unsafe(() -> method.invoke(action, a, b)));
            return new PacketPlayOutPlayerInfo(data);
        });
    }

    public static void initEventHandler(Plugin plugin) {
        EventManager.init(plugin);
    }

    public enum EntityState implements EnumUtil.Maskable<EntityState> {

        DEFAULT(0x00),
        ON_FIRE(0x01),
        @Deprecated CROUCHING(0x02),
        @Deprecated UNUSED(0x04),
        SPRINTING(0x08),
        SWIMMING(0x10),
        INVISIBLE(0x20),
        GLOWING(0x40),
        FLYING(0x80);

        private final int mask;

        EntityState(int mask) {
            this.mask = mask;
        }

        public int getMask() {
            return mask;
        }
    }

    public enum SkinStatus implements EnumUtil.Maskable<SkinStatus> {

        CAPE_ENABLED(0x01),
        JACKET_ENABLED(0x02),
        LEFT_SLEEVE_ENABLED(0x04),
        RIGHT_SLEEVE_ENABLED(0x08),
        LEFT_PANTS_LEG_ENABLED(0x10),
        RIGHT_PANTS_LEG_ENABLED(0x20),
        HAT_ENABLED(0x40),
        @Deprecated UNUSED(0x80);

        private static final SkinStatus[] ALL = {CAPE_ENABLED, JACKET_ENABLED, LEFT_SLEEVE_ENABLED, RIGHT_SLEEVE_ENABLED, LEFT_PANTS_LEG_ENABLED, RIGHT_PANTS_LEG_ENABLED, HAT_ENABLED};
        private final int mask;

        SkinStatus(int mask) {
            this.mask = mask;
        }

        public int getMask() {
            return mask;
        }
    }

    public enum Pose implements EnumUtil.Identifiable<EntityPose> {

        STANDING(EntityPose.a),
        FALL_FLYING(EntityPose.b),
        SLEEPING(EntityPose.c),
        SWIMMING(EntityPose.d),
        SPIN_ATTACK(EntityPose.e),
        CROUCHING(EntityPose.f),
        LONG_JUMPING(EntityPose.g),
        DYING(EntityPose.h),
        CROAKING(EntityPose.i),
        ROARING(EntityPose.j),
        SNIFFING(EntityPose.k),
        EMERGING(EntityPose.l),
        DIGGING(EntityPose.m);

        private final EntityPose nmsPose;

        Pose(EntityPose nmsPose) {
            this.nmsPose = nmsPose;
        }

        public EntityPose getID() {
            return nmsPose;
        }

    }

    public enum HandStatus implements EnumUtil.Maskable<HandStatus> {

        MAIN_HAND(0x00),
        HAND_ACTIVE(0x01),
        OFF_HAND(0x02),
        RIPTIDE_SPIN_ATTACK(0x04);

        private final int mask;

        HandStatus(int mask) {
            this.mask = mask;
        }

        public int getMask() {
            return mask;
        }
    }

    public enum Hand implements EnumUtil.BiIdentifiable<EnumHand, Integer> {

        OFF_HAND(EnumHand.b, 1),
        MAIN_HAND(EnumHand.a, 0);

        private final EnumHand id;
        private final int type;

        Hand(EnumHand id, int type) {
            this.id = id;
            this.type = type;
        }

        public Integer getSecondID() {
            return type;
        }

        public EnumHand getID() {
            return id;
        }

    }

    public enum InteractType implements EnumUtil.Identifiable<String> {

        RIGHT_CLICK("INTERACT"),
        LEFT_CLICK("ATTACK"),
        RIGHT_CLICK_AT("INTERACT_AT");

        private final String id;

        InteractType(String id) {
            this.id = id;
        }

        public String getID() {
            return id;
        }
    }

    public enum PlayerInfo {

        ADD_PLAYER(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a),
        UPDATE_GAME_MODE(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b),
        UPDATE_LATENCY(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.c),
        UPDATE_DISPLAY_NAME(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.d),
        REMOVE_PLAYER(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e);

        private final PacketPlayOutPlayerInfo.EnumPlayerInfoAction nmsAction;

        PlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction nmsAction) {
            this.nmsAction = nmsAction;
        }

        public PacketPlayOutPlayerInfo.EnumPlayerInfoAction getNMSAction() {
            return nmsAction;
        }
    }

    public enum Ping {

        NO_CONNECTION(-1),
        ONE_BAR(1000),
        TWO_BARS(999),
        THREE_BARS(599),
        FOUR_BARS(299),
        FIVE_BARS(149);

        private final int milliseconds;

        Ping(int milliseconds) {
            this.milliseconds = milliseconds;
        }

        public int getMilliseconds() {
            return milliseconds;
        }
    }

    public enum ItemSlot {

        MAIN_HAND(EnumItemSlot.a),
        OFF_HAND(EnumItemSlot.b),
        BOOTS(EnumItemSlot.c),
        LEGGINGS(EnumItemSlot.d),
        CHESTPLATE(EnumItemSlot.e),
        HELMET(EnumItemSlot.f);

        private final EnumItemSlot nmsItemSlot;

        ItemSlot(EnumItemSlot nmsItemSlot) {
            this.nmsItemSlot = nmsItemSlot;
        }

        public EnumItemSlot getNMSItemSlot() {
            return nmsItemSlot;
        }
    }

    public enum GlowColor {

        BLACK(EnumChatFormat.a),
        DARK_BLUE(EnumChatFormat.b),
        DARK_GREEN(EnumChatFormat.c),
        DARK_AQUA(EnumChatFormat.d),
        DARK_RED(EnumChatFormat.e),
        DARK_PURPLE(EnumChatFormat.f),
        GOLD(EnumChatFormat.g),
        GRAY(EnumChatFormat.h),
        DARK_GRAY(EnumChatFormat.i),
        BLUE(EnumChatFormat.j),
        GREEN(EnumChatFormat.k),
        AQUA(EnumChatFormat.l),
        RED(EnumChatFormat.m),
        LIGHT_PURPLE(EnumChatFormat.n),
        YELLOW(EnumChatFormat.o),
        WHITE(EnumChatFormat.p),
        NONE(EnumChatFormat.v);

        private final EnumChatFormat nmsColor;

        GlowColor(EnumChatFormat nmsColor) {
            this.nmsColor = nmsColor;
        }

        public EnumChatFormat getNMSColor() {
            return nmsColor;
        }
    }

    public enum GameMode {

        SURVIVAL(EnumGamemode.a),
        CREATIVE(EnumGamemode.b),
        ADVENTURE(EnumGamemode.c),
        SPECTATOR(EnumGamemode.d);

        private final EnumGamemode nmsGameMode;

        GameMode(EnumGamemode nmsGameMode) {
            this.nmsGameMode = nmsGameMode;
        }

        public EnumGamemode getNMSGameMode() {
            return nmsGameMode;
        }
    }

    public enum Animation {

        SWING_MAIN_HAND(0),
        TAKE_DAMAGE(1),
        LEAVE_BED(2),
        SWING_OFFHAND(3),
        CRITICAL_EFFECT(4),
        MAGIC_CRITICAL_EFFECT(5);

        private final int type;

        Animation(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

    }

    public class MetaData {

        //Entity metadata
        private final DataWatcher.Item<Byte> entityState = a(0, (byte) EnumUtil.createMask(EntityState.DEFAULT));
        private final DataWatcher.Item<Integer> airTicks = a(1, 300);
        private final DataWatcher.Item<Optional<IChatBaseComponent>> customName = a(2, Optional.empty(), DataWatcherRegistry.f);
        private final DataWatcher.Item<Boolean> customNameVisible = a(3, false);
        private final DataWatcher.Item<Boolean> silent = a(4, false);
        private final DataWatcher.Item<Boolean> noGravity = a(5, false);
        private final DataWatcher.Item<EntityPose> pose = a(6, Pose.STANDING.getID());
        private final DataWatcher.Item<Integer> frozenTicks = a(7, 0); //shaking at tick 140

        //LivingEntity metadata
        private final DataWatcher.Item<Byte> handStatus = a(8, (byte) EnumUtil.createMask(HandStatus.MAIN_HAND));
        private final DataWatcher.Item<Float> health = a(9, 1.0F);
        private final DataWatcher.Item<Integer> potionEffectColor = a(10, 0);
        private final DataWatcher.Item<Boolean> isPotionEffectAmbient = a(11, false);
        private final DataWatcher.Item<Integer> arrowsInEntity = a(12, 0);
        private final DataWatcher.Item<Integer> beeStingers = a(13, 0);
        private final DataWatcher.Item<Optional<BlockPosition>> sleepingBedLocation = a(14, Optional.empty(), DataWatcherRegistry.m);

        //Player metadata
        private final DataWatcher.Item<Float> additionalHearts = a(15, 0.0F);
        private final DataWatcher.Item<Integer> score = a(16, 0);
        private final DataWatcher.Item<Byte> skinStatus = a(17, (byte) EnumUtil.createMask(SkinStatus.ALL));
        private final DataWatcher.Item<Byte> hand = a(18, (byte) ((int) Hand.MAIN_HAND.getSecondID()));
        private final DataWatcher.Item<NBTTagCompound> leftShoulder = a(19, new NBTTagCompound());
        private final DataWatcher.Item<NBTTagCompound> rightShoulder = a(20, new NBTTagCompound());

        private final List<DataWatcher.Item<?>> list;

        public MetaData() {
            this.list = new ArrayList<>(Arrays.asList(
                    this.entityState,
                    this.airTicks,
                    this.customName,
                    this.customNameVisible,
                    this.silent,
                    this.noGravity,
                    this.pose,
                    this.frozenTicks,
                    this.handStatus,
                    this.health,
                    this.potionEffectColor,
                    this.isPotionEffectAmbient,
                    this.arrowsInEntity,
                    this.beeStingers,
                    this.sleepingBedLocation,
                    this.additionalHearts,
                    this.score,
                    this.skinStatus,
                    this.hand,
                    this.leftShoulder,
                    this.rightShoulder));
        }

        public List<EntityState> getEntityState() {
            return EnumUtil.fromMask(entityState.b(), EntityState.class);
        }

        public Integer getAirTicks() {
            return airTicks.b();
        }

        public Optional<IChatBaseComponent> getCustomName() {
            return customName.b();
        }

        public Boolean isCustomNameVisible() {
            return customNameVisible.b();
        }

        public Boolean isSilent() {
            return silent.b();
        }

        public Boolean hasNoGravity() {
            return noGravity.b();
        }

        public Pose getPose() {
            return EnumUtil.getByID(this.pose.b(), Pose.class);
        }

        public Integer getFrozenTicks() {
            return frozenTicks.b();
        }

        public List<HandStatus> getHandStatus() {
            return EnumUtil.fromMask(handStatus.b(), HandStatus.class);
        }

        public Float getHealth() {
            return health.b();
        }

        public Integer getPotionEffectColor() {
            return potionEffectColor.b();
        }

        public Boolean isPotionEffectAmbient() {
            return isPotionEffectAmbient.b();
        }

        public Integer getArrowsInEntity() {
            return arrowsInEntity.b();
        }

        public Integer getBeeStingers() {
            return beeStingers.b();
        }

        public Optional<BlockPosition> getSleepingBedLocation() {
            return sleepingBedLocation.b();
        }

        public Float getAdditionalHearts() {
            return additionalHearts.b();
        }

        public Integer getScore() {
            return score.b();
        }

        public List<SkinStatus> getSkinStatus() {
            return EnumUtil.fromMask(skinStatus.b(), SkinStatus.class);
        }

        public Hand getHand() {
            return EnumUtil.getBySecondID((int) hand.b(), Hand.class);
        }

        public NBTTagCompound getLeftShoulder() {
            return leftShoulder.b();
        }

        public NBTTagCompound getRightShoulder() {
            return rightShoulder.b();
        }

        public void setEntityState(EntityState... entityState) {
            this.entityState.a((byte) EnumUtil.createMask(entityState));
        }

        public void setAirTicks(Integer airTicks) {
            this.airTicks.a(airTicks);
        }

        public void setCustomName(String customName) {
            this.customName.a(Optional.ofNullable(IChatBaseComponent.a(customName)));
        }

        public void setCustomNameVisible(Boolean customNameVisible) {
            this.customNameVisible.a(customNameVisible);
        }

        public void setSilent(Boolean silent) {
            this.silent.a(silent);
        }

        public void setNoGravity(Boolean gravity) {
            this.noGravity.a(gravity);
        }

        public void setPose(Pose pose) {
            this.pose.a(pose.getID());
        }

        public void setFrozenTicks(Integer frozenTicks) {
            this.frozenTicks.a(frozenTicks);
        }

        public void setShaking(boolean shaking) {
            this.setFrozenTicks(shaking ? 140 : 0);
        }

        public void setHandStatus(HandStatus handStatus) {
            this.handStatus.a((byte) EnumUtil.createMask(handStatus));
        }

        public void setHealth(Float health) {
            this.health.a(health);
        }

        public void setPotionEffectColor(Integer potionEffectColor) {
            this.potionEffectColor.a(potionEffectColor);
        }

        public void setIsPotionEffectAmbient(Boolean isPotionEffectAmbient) {
            this.isPotionEffectAmbient.a(isPotionEffectAmbient);
        }

        public void setArrowsInEntity(Integer arrowsInEntity) {
            this.arrowsInEntity.a(arrowsInEntity);
        }

        public void setBeeStingers(Integer beeStingers) {
            this.beeStingers.a(beeStingers);
        }

        public void setSleepingBedLocation(BlockPosition sleepingBedLocation) {
            this.sleepingBedLocation.a(Optional.ofNullable(sleepingBedLocation));
        }

        public void setAdditionalHearts(Float additionalHearts) {
            this.additionalHearts.a(additionalHearts);
        }

        public void setScore(Integer score) {
            this.score.a(score);
        }

        public void setSkinStatus(SkinStatus... skinStatus) {
            this.skinStatus.a((byte) EnumUtil.createMask(skinStatus));
        }

        public void setHand(Hand hand) {
            this.hand.a((byte) ((int) hand.getSecondID()));
        }

        public void setLeftShoulder(NBTTagCompound leftShoulder) {
            this.leftShoulder.a(leftShoulder);
        }

        public void setRightShoulder(NBTTagCompound rightShoulder) {
            this.rightShoulder.a(rightShoulder);
        }

        public List<DataWatcher.Item<?>> getList() {
            return list;
        }

        private static <T> DataWatcher.Item<T> a(int index, T value) {
            DataWatcherSerializer<?> serializer = null;

            if (value instanceof Byte) {
                serializer = DataWatcherRegistry.a;
            } else if (value instanceof Float) {
                serializer = DataWatcherRegistry.c;
            } else if (value instanceof Integer) {
                serializer = DataWatcherRegistry.b;
            } else if (value instanceof String) {
                serializer = DataWatcherRegistry.d;
            } else if (value instanceof Boolean) {
                serializer = DataWatcherRegistry.i;
            } else if (value instanceof NBTTagCompound) {
                serializer = DataWatcherRegistry.q;
            } else if (value instanceof BlockPosition) {
                serializer = DataWatcherRegistry.m;
            } else if (value instanceof IChatBaseComponent) {
                serializer = DataWatcherRegistry.e;
            } else if (value instanceof EntityPose) {
                serializer = DataWatcherRegistry.t;
            }
            return a(index, value, (DataWatcherSerializer<T>) serializer);
        }

        private static <T> DataWatcher.Item<T> a(int index, T value, DataWatcherSerializer<T> serializer) {
            return new DataWatcher.Item<T>(new DataWatcherObject<T>(index, serializer), value);
        }

    }

    public static class EnumUtil {

        private interface Maskable<M> {
            int getMask();
        }

        private interface Identifiable<I> {

            I getID();

        }

        private interface BiIdentifiable<I, J> extends Identifiable<I> {

            J getSecondID();
        }

        @SafeVarargs
        private static <M> int createMask(Maskable<M>... maskables) {
            int mask = 0;
            for (Maskable m : maskables) {
                mask |= m.getMask();
            }
            return mask;
        }

        private static <M extends Maskable<M>> List<M> fromMask(int mask, Class<M> enumClass) {
            List<M> list = new ArrayList<M>();
            for (M maskable : enumClass.getEnumConstants()) {
                if ((maskable.getMask() & mask) != 0) {
                    list.add(maskable);
                }
            }
            return list;
        }

        private static <I, M extends Identifiable<I>> M getByID(I id, Class<M> enumClass) {
            for (M identifiable : enumClass.getEnumConstants()) {
                if (id == identifiable.getID()) {
                    return identifiable;
                }
            }
            return null;
        }

        private static <I, J, M extends BiIdentifiable<I, J>> M getBySecondID(J id, Class<M> enumClass) {
            for (M identifiable : enumClass.getEnumConstants()) {
                if (id == identifiable.getID()) {
                    return identifiable;
                }
            }
            return null;
        }
    }

    public static class SkinTextures {

        private static final String TEXTURE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";
        private static final String UUID_URL = "https://api.mojang.com/profiles/minecraft";

        private final String texture;
        private final String signature;

        public SkinTextures(String textures, String signature) {
            this.texture = textures;
            this.signature = signature;
        }

        public String getTexture() {
            return texture;
        }

        public String getSignature() {
            return signature;
        }

        public static BukkitCompletable<SkinTextures> getByUsername(Plugin plugin, String username) {
            return BukkitCompletable.supplyASync(plugin, ()->{
                JSONArray array = new JSONArray();
                array.add(username);
                UUID uuid = null;
                HttpRequest request = HttpRequest.newBuilder(new URI(UUID_URL))
                        .setHeader("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(array.toString()))
                        .timeout(Duration.ofSeconds(5))
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                    JSONArray uuidArray = (JSONArray) new JSONParser().parse(response.body());
                    if (uuidArray.size() > 0) {
                        String uuidStr = (String) ((JSONObject) uuidArray.get(0)).get("id");
                        uuid = UUID.fromString(uuidStr.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
                    }
                }
                if (uuid == null) throw new NullPointerException("uuid is null");
                return getByUUID(plugin, uuid).getSync();
            });
        }

        public static BukkitCompletable<SkinTextures> getByUUID(Plugin plugin, UUID uuid) {
            return BukkitCompletable.supplyASync(plugin, ()->{
                SkinTextures result = null;
                HttpRequest request = HttpRequest.newBuilder(new URI(String.format(TEXTURE_URL, uuid.toString().replace("-", ""))))
                        .timeout(Duration.ofSeconds(5))
                        .GET()
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                    JSONArray properties = (JSONArray) ((JSONObject) new JSONParser().parse(response.body())).get("properties");
                    for (int t = 0; t < properties.size(); t++) {
                        JSONObject obj = (JSONObject) properties.get(t);
                        if (obj.containsKey("name") && obj.get("name").equals("textures")) {
                            result = new SkinTextures((String) obj.get("value"), (String) obj.get("signature"));
                        }
                    }
                }
                return result;
            });
        }
    }

    public static class BukkitCompletable<T> {

        private final Plugin plugin;
        private final UnsafeSupplier<T> runnable;
        private Consumer<Throwable> errorHandler;
        private Consumer<T> callbackHandler;
        private Runnable emptyCallbackHandler;

        private BukkitCompletable(Plugin plugin, UnsafeSupplier<T> runnable) {
            this.plugin = plugin;
            this.runnable = runnable;
        }

        public static <T> BukkitCompletable<T> supplyASync(Plugin plugin, UnsafeSupplier<T> runnable) {
            return new BukkitCompletable<T>(plugin, runnable);
        }

        public BukkitCompletable<T> onFinish(Consumer<T> callbackHandler) {
            this.callbackHandler = callbackHandler;
            return this;
        }

        public BukkitCompletable<T> onEmptyFinish(Runnable emptyCallbackHandler) {
            this.emptyCallbackHandler = emptyCallbackHandler;
            return this;
        }

        public BukkitCompletable<T> onException(Consumer<Throwable> errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        public void getSafe() {
            this.get(true);
        }

        public void getUnsafe() {
            this.get(false);
        }

        public T getSync() throws Exception {
            return runnable.get();
        }

        private void get(boolean safe) {
            async(plugin, ()->{
                try {
                    T t = this.runnable.get();
                    if(!(this.callbackHandler == null && this.emptyCallbackHandler == null)) {
                        if(safe) {
                            sync(plugin, () -> {
                                if (callbackHandler != null) this.callbackHandler.accept(t);
                                if (emptyCallbackHandler != null) this.emptyCallbackHandler.run();
                            });
                        } else {
                            if(callbackHandler != null) this.callbackHandler.accept(t);
                            if(emptyCallbackHandler != null) this.emptyCallbackHandler.run();
                        }
                    }
                } catch (Throwable e) {
                    if(this.errorHandler != null) {
                        if(safe) sync(plugin, ()->this.errorHandler.accept(e));
                        else this.errorHandler.accept(e);
                    }
                }
            });
        }
    }

    private static class EventManager implements Listener {

        private static final String CHANNEL_NAME = "npc_manager";
        private static EventManager INSTANCE;

        private final Plugin plugin;
        private final Map<Integer, NPCListener> listenerMap = new HashMap<>();

        private EventManager(Plugin plugin) {
            this.plugin = plugin;
            Bukkit.getPluginManager().registerEvents(this, plugin);
            Bukkit.getOnlinePlayers().forEach(this::unregisterPlayer);
            Bukkit.getOnlinePlayers().forEach(this::registerPlayer);
        }

        private void listenNPC(NPC npc, NPCListener listener) {
            if (listenerMap.containsKey(npc.getEntityID()))
                throw new UnsupportedOperationException("cannot register same npc twice");
            this.listenerMap.put(npc.getEntityID(), listener);
        }

        private void unlistenNPC(NPC npc) {
            if (!listenerMap.containsKey(npc.getEntityID())) throw new NullPointerException("listener does not exist");
            this.listenerMap.remove(npc.getEntityID());
        }

        private void unregisterPlayer(Player player) {
            ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.b.m.pipeline();
            if(pipeline.names().contains(CHANNEL_NAME)) {
                pipeline.remove(CHANNEL_NAME);
            }
        }

        private void registerPlayer(Player player) {
            ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.b.m.pipeline();
            if(!pipeline.names().contains(CHANNEL_NAME)) {
                pipeline.addBefore("packet_handler", CHANNEL_NAME, new PlayerInboundHandlerAdapter(player));
            }
        }

        @EventHandler(priority = EventPriority.LOWEST)
        public void onPlayerJoinEvent(PlayerJoinEvent e) {
            this.registerPlayer(e.getPlayer());
        }

        public static boolean isInitialized() {
            return INSTANCE != null;
        }

        public static void init(Plugin plugin) {
            if(INSTANCE != null) INSTANCE.listenerMap.clear();
            INSTANCE = new EventManager(plugin);
        }

        private class PlayerInboundHandlerAdapter extends ChannelInboundHandlerAdapter {

            private final Player player;

            public PlayerInboundHandlerAdapter(Player player) {
                this.player = player;
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                super.channelRead(ctx, msg);
                try {
                    if (msg instanceof PacketPlayInUseEntity packet) {
                        Field entityIdField = packet.getClass().getDeclaredField("a");
                        entityIdField.setAccessible(true);
                        int entityId = (int) entityIdField.get(packet);
                        final NPCListener listener = listenerMap.getOrDefault(entityId, null);
                        if (listener != null) {
                            Field typeField = packet.getClass().getDeclaredField("b");
                            typeField.setAccessible(true);
                            Object type = typeField.get(packet);
                            Method typeMethod = type.getClass().getDeclaredMethod("a");
                            typeMethod.setAccessible(true);
                            InteractType interactType = EnumUtil.getByID(((Enum) typeMethod.invoke(type)).name(), InteractType.class);
                            Hand hand = Hand.MAIN_HAND;
                            if (interactType != InteractType.RIGHT_CLICK_AT) {
                                if (interactType == InteractType.RIGHT_CLICK) {
                                    Field handField = type.getClass().getDeclaredField("a");
                                    handField.setAccessible(true);
                                    hand = EnumUtil.getByID((EnumHand) handField.get(type), Hand.class);
                                }
                                boolean sneaking = packet.b();
                                final NPCInteractEvent event = new NPCInteractEvent(this.player, interactType, hand, sneaking);
                                Bukkit.getScheduler().runTask(plugin, () -> listener.accept(event));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ;
            }
        }
    }

    public record TeamFeatures(boolean nameTagVisible, boolean collision, GlowColor glowColor) {}

    public record NPCInteractEvent(Player player, InteractType interactType, Hand hand, boolean sneaking) {
    }

    public static void sync(Plugin plugin, Runnable runnable) {
        Bukkit.getScheduler().runTask(plugin, runnable);
    }

    public static void async(Plugin plugin, Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {}
    }

    private static void unsafe(UnsafeRunnable run) {
        try {
            run.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> T createDataSerializer(UnsafeFunction<PacketDataSerializer, T> callback) {
        PacketDataSerializer data = new PacketDataSerializer(Unpooled.buffer());
        T result = null;
        try {
            result = callback.apply(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            data.release();
        }
        return result;
    }

    @FunctionalInterface
    private interface UnsafeSupplier<T> {
        T get() throws Exception;
    }

    @FunctionalInterface
    private interface UnsafeRunnable {
        void run() throws Exception;
    }

    @FunctionalInterface
    private interface UnsafeFunction<K, T> {
        T apply(K k) throws Exception;
    }

    @FunctionalInterface
    public interface NPCListener extends Consumer<NPCInteractEvent> {
    }
}