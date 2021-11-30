# GameName
Basic Hello World Android Game Framework by Linus Sphinx

git clone git@github.com:Sphinxtar/GameName.git<br/>
cd GameName<br/>
./changeGameName.sh BikerBrawl or what ever you want to name your new game project<br/>
fire up android studio, use ... menu at right corner to import gradle project, let gradle sync<br/>
From file menu -> 'invalidate cache and restart'<br/>
hit the debug button, should fire up emulator and draw a white rect in corner of a blue screen<br/>
git init, add, commit and push to your own repository, see gtiwtf.txt<br/>
Game engine revolves around gstate, the game status code. 
0 is playing the game
1 or 2 is showing a button menu to tap
3 and up is for slides/static images to display and wait for touch
when you initialize the values you set the, 'gcode', of the menu or
slide to the value you want gstate changed to. That is how to chain
menus or help screens together.
