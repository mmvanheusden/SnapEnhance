package me.rhunk.snapenhance.common.config.impl

import me.rhunk.snapenhance.common.config.ConfigContainer
import me.rhunk.snapenhance.common.config.FeatureNotice
import me.rhunk.snapenhance.common.data.NotificationType

class MessagingTweaks : ConfigContainer() {
    val anonymousStoryViewing = boolean("anonymous_story_viewing")
    val hideBitmojiPresence = boolean("hide_bitmoji_presence")
    val hideTypingNotifications = boolean("hide_typing_notifications")
    val unlimitedSnapViewTime = boolean("unlimited_snap_view_time")
    val disableReplayInFF = boolean("disable_replay_in_ff")
    val messagePreviewLength = integer("message_preview_length", defaultValue = 20)
    val callStartConfirmation = boolean("call_start_confirmation") { requireRestart() }
    val autoSaveMessagesInConversations = multiple("auto_save_messages_in_conversations",
        "CHAT",
        "SNAP",
        "NOTE",
        "EXTERNAL_MEDIA",
        "STICKER"
    ) { requireRestart() }
    val preventMessageSending = multiple("prevent_message_sending", *NotificationType.getOutgoingValues().map { it.key }.toTypedArray()) {
        customOptionTranslationPath = "features.options.notifications"
        nativeHooks()
    }
    val betterNotifications = multiple("better_notifications", "snap", "chat", "reply_button", "download_button", "group") { requireRestart() }
    val notificationBlacklist = multiple("notification_blacklist", *NotificationType.getIncomingValues().map { it.key }.toTypedArray()) {
        customOptionTranslationPath = "features.options.notifications"
    }
    val messageLogger = boolean("message_logger") { addNotices(FeatureNotice.UNSTABLE); requireRestart() }
    val galleryMediaSendOverride = boolean("gallery_media_send_override") { nativeHooks() }
    val bypassMessageRetentionPolicy = boolean("bypass_message_retention_policy") { addNotices(FeatureNotice.UNSTABLE); requireRestart() }
}