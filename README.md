# GameName
Basic Hello World Android Game Framework
git clone git@github.com:Sphinxtar/GameName.git<br/>
cd GameName<br/>
./changeGameName.sh BikerBrawl or what ever you want to name your new game project<br/>
let gradle sync<br/>
From file menu -> 'invalidate cache and restart'<br/>
hit the debug button, should fire up emulator and draw a splash screen<br/>
git init, add, commit and push to your own repository, see gtiwtf.txt<br/>
Game engine revolves around gstate, the game status code.<br/>
0 is playing the game<br/>
1 is showing a button menu to tap<br/>
3 and up is for slides/static images to display and await touch<br/>
adjust those values for more game states, menus, slides etc.<br/>
When you initialize the values set the, 'gcode', of the menu or
slide item to the value you want gstate changed to by tapping it or one of it's buttons.<br/>
That is how to chain menus or help screens together.<br/>
It starts with gstate=2 for the splash screen and on tap changes to 
gstate=1 to switch to the main menu.

Proof Of Concept: 
https://github.com/Sphinxtar/AtomSmasher/
