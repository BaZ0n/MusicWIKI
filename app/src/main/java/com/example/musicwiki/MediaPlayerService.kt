package com.example.musicwiki

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MediaPlayerService : Service() {
    //    private lateinit var mediaSession: MediaSessionCompat
//    private lateinit var mediaPlayer: MediaPlayer
//    private val CHANNEL_ID = "media_playback"
//
//    override fun onCreate() {
//        super.onCreate()
//
//        mediaPlayer = MediaPlayer()
//        mediaSession = MediaSessionCompat(this, "MediaPlayerService")
//
//        createNotificationChannel()
//        buildNotification(false)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release()
//        mediaSession.release()
//    }
//
//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Media Playback"
//            val description = "Control media playback"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                this.description = description
//            }
////            val notificationManager = getSystemService(NotificationManager::class.java)
////            notificationManager.createNotificationChannel(channel)
//            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun buildNotification(flag: Boolean) {
//
//        val serviceIntent = Intent(this, MediaPlayerService::class.java)
//        val pendingServiceIntent = PendingIntent.getService(this, 0, serviceIntent, PendingIntent.FLAG_IMMUTABLE)
//
//
//        val notificationInPlay = NotificationCompat.Builder(baseContext, CHANNEL_ID)
//            .setContentTitle("Media Title")
//            .setContentText("Media Description")
//            .setSmallIcon(android.R.drawable.ic_media_play)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setStyle(
//                androidx.media.app.NotificationCompat.MediaStyle()
//                    .setMediaSession(mediaSession.sessionToken)
//            )
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setOnlyAlertOnce(true)
//            .addAction(R.drawable.previousicon, "", null)
//            .addAction(R.drawable.pauseicon, "", pendingServiceIntent)
//            .addAction(R.drawable.nexticon, "", null)
//            .build()
//
//        val notificationInPause = NotificationCompat.Builder(baseContext, "media_playback")
//            .setContentTitle("Media Title")
//            .setContentText("Media Description")
//            .setSmallIcon(android.R.drawable.ic_media_play)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setStyle(
//                androidx.media.app.NotificationCompat.MediaStyle()
//                    .setMediaSession(mediaSession.sessionToken)
//            )
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setOnlyAlertOnce(true)
//            .addAction(R.drawable.previousicon, "", null)
//            .addAction(R.drawable.playicon, "", pendingServiceIntent)
//            .addAction(R.drawable.nexticon, "", null)
//            .build()
//
//        if (flag) {
//            startForeground(15, notificationInPause)
//        }
//        else {
//            startForeground(15, notificationInPlay)
//        }
//    }
//
//    private fun handlePlayButton() {
//        if (!mediaPlayer.isPlaying) {
//            mediaPlayer.start()
//            // Обновите уведомление, чтобы отобразить актуальное состояние воспроизведения
//            updateNotification(false)
//        }
//        else {
//            mediaPlayer.pause()
//            updateNotification(true)
//        }
//    }
//
//    private fun updateNotification(flag: Boolean) {
//        buildNotification(flag)
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}