/**
 * Copyright (c) 2016, Finwe Ltd. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation and/or
 *    other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package fi.finwe.orion360.sdk.pro.examples.minimal;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import fi.finwe.orion360.SimpleOrionActivity;
import fi.finwe.orion360.sdk.pro.examples.MainMenu;
import fi.finwe.orion360.sdk.pro.examples.R;
import fi.finwe.orion360.v3.source.OrionVideoTexture;

/**
 * An example of a tiny Orion360 player, by inheriting from SimpleOrionActivity.
 * <p>
 * Features:
 * <ul>
 * <li>Plays one hard-coded full spherical (360x180) equirectangular video
 * <li>Creates a fullscreen view locked to landscape orientation
 * <li>Auto-starts playback on load and stops when playback is completed
 * <li>Renders the video using standard rectilinear projection
 * <li>Allows navigation with touch & movement sensors (if supported by HW) as follows:
 * <ul>
 * <li>Panning (gyro or swipe)
 * <li>Zooming (pinch)
 * <li>Tilting (pinch rotate)
 * </ul>
 * <li>Auto Horizon Aligner (AHL) feature straightens the horizon</li>
 * </ul>
 */
public class TinyPlayer extends SimpleOrionActivity {

    /** Buffering indicator, to be shown while buffering video from the network. */
    private ProgressBar mBufferingIndicator;


	@Override
	public void onCreate(Bundle savedInstanceState) {

        // Call super class implementation first to create the necessary Orion360 objects, and
        // to bind them together to form a simple player configuration.

        // Notice that this call will fail if a valid Orion360 license file for the package name
        // (defined in the application's manifest/build.gradle file) cannot be found.
		super.onCreate(savedInstanceState);

        // Set layout.
		setContentView(R.layout.activity_video_player);

        // Set Orion360 view (defined in the XML layout) that will be used for rendering content.
        setOrionView(R.id.orion_view);

        // Get buffering indicator.
        mBufferingIndicator = (ProgressBar) findViewById(R.id.buffering_indicator);

        // Set a URI to an .mp4 video-on-demand stream (encode video with web/progressive
        // setting enabled for best performance).

        // Notice that accessing video streams over a network connection requires INTERNET
        // permission to be specified in the manifest file.
        setContentUri(MainMenu.TEST_VIDEO_URI_1280x640);

        // When you run the app, you may get a warning from MediaPlayer component to the LogCat:
        // W/MediaPlayer: Couldn't open []: java.io.FileNotFoundException: No content provider: []
        // Here Android MediaPlayer is using an exception for control flow; you can disregard it.

	}

    @Override
    public void onVideoBufferingStart(OrionVideoTexture orionVideoTexture) {
        mBufferingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onVideoBufferingEnd(OrionVideoTexture orionVideoTexture) {
        mBufferingIndicator.setVisibility(View.GONE);
    }

}
