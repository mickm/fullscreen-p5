package fullscreen.tests;

import japplemenubar.JAppleMenuBar;

import org.junit.Test;

import processing.core.PApplet;

import fullscreen.FullScreen;
import fullscreen.FullScreenTools;
import fullscreen.SoftFullScreen;

/**
 * Tests a few things semi-automatically... 
 * @author hansi
 */
public class Tests {

	/**
	 * Tests if hiding the menu-bar works on mac-os
	 * @throws InterruptedException 
	 */
	@Test
	public void testMenuBar() throws InterruptedException{
		if( PApplet.platform == PApplet.MACOSX ){
			JAppleMenuBar menuBar = new JAppleMenuBar(); 
			menuBar.setVisible( false ); 
			Thread.sleep( 500 ); 
			menuBar.setVisible( true ); 
		}
	}
	
	/**
	 * Creates a SoftFullScreen object on screen 0 and 1.
	 */
	@Test
	public void softFS() throws Exception{
		for( int screenNr = 0; screenNr < FullScreenTools.devices().length; screenNr ++ ){
			Demo.Simple sketch = new Demo.Simple();
			SoftFullScreen fs = new SoftFullScreen( sketch, screenNr );
			
			Thread.sleep( 1000 );
			for( int i = 0; i < 10; i++ ){
				fs.setFullScreen( true );
				Thread.sleep( 500 ); 
				fs.setFullScreen( false );
				Thread.sleep( 500 );
			}
			
			sketch.frame.setVisible( false ); 
			sketch.stop(); 
			sketch.destroy(); 
		}
		// if we haven't crashed until now we're good! 
	}
	
	/**
	 * Creates the "classic" FS object on screen 0 and 1. 
	 */
	@Test
	public void classicFS() throws Exception{ 
		for( int screenNr = 0; screenNr < 2; screenNr ++ ){
			Demo.Simple sketch = new Demo.Simple();
			FullScreen fs = new FullScreen( sketch, screenNr );
			fs.setFullScreen( true );
			
			Thread.sleep( 2000 ); 
			fs.setFullScreen( false ); 
			
			Thread.sleep( 2000 );
			
			sketch.frame.setVisible( false ); 
			sketch.stop(); 
			sketch.destroy(); 
		}
	}
	
	/**
	 * Test split soft-fullscreen
	 */
	@Test
	public void testSplit() throws Exception{
		Demo.Simple sketch = new Demo.Simple();
		SoftFullScreen fs = new SoftFullScreen( sketch );
		fs.setScreens(
			0, 0, 0, 400, 400, 
			1, 0, 0, 800, 800 
		);
		Thread.sleep( 1000 );
		for( int i = 0; i < 10; i++ ){
			fs.setFullScreen( true );
			Thread.sleep( 500 ); 
			fs.setFullScreen( false );
			Thread.sleep( 500 );
		}
			
		sketch.frame.setVisible( false ); 
		sketch.stop(); 
		sketch.destroy(); 
	}
}