When it comes to updating westerosblock.json, the update 'loop' is essentially
the same - but the stuff to be copied back into the source tree is a bit more
complicated.  
Look at https://github.com/WesterosCraft/WesterosBlocks/blob/1.16.5/copyFiles.sh 
for the shell script I use about 20 times an evening to transfer the generated 
content from by test server directory (~/forge-1.16.5) to my source tree.

You'll want to adjust it for yourself, but it shows the flow of what to clean up vs copy,
particularly since the newer westerosblocks.json features (like the condStates for biomes and Y coordinate limits, and the basic CTM featues like random, vertical, horizontal)
all result in changes in the set of generated model files and such.

So my basic loop for doing westerosblocks.json relevant updates winds up looking like:
1) Update the code or westerosblocks.json
2) Run './gradlew build'
3) Copy build/libs/WesterosBlocks-5.0.0-alpha-1.jar to my test forge server mod directory (~/forge-1.16.5/mods)
4) Start the server, and tell it to stop as soon as it's done starting (stop command)
5) Run './copyFiles.sh' to copy new generated content back to source tree
6) Run './gradlew build' again, this time to generate the full updated mod (including the generated resources)
7) Copy build/libs/WesterosBlocks-5.0.0-alpha-1 to my test forge client's mod directory (~/forgeclient-1.16.5/mods)
8) Fire up test client, and look at the pretty blocks! 