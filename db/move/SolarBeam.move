<Move>
  <name>SolarBeam</name>
  <type>GRASS</type>
  <PP>10</PP>
  <accuracy>0</accuracy>
  <speed>0</speed>
  <DT>SPECIAL</DT>
  <effect>
    <require>
	</require>
    <effect>
	  <SetFlag>
	    <target>user</target>
		<flag>6</flag>
		<text>$target is gathering sunlight!</text>
	  </SetFlag>
	  <SetCounter>
	    <target>user</target>
	    <counter>6</counter>
	    <type>set</type>
	    <n>1</n>
	  </SetCounter>
    </effect>
  </effect>
  <isContact>false</isContact>
  <isProtectable>false</isProtectable>
  <isReflectable>false</isReflectable>
  <isSnatchable>false</isSnatchable>
  <isMirrorable>true</isMirrorable>
  <isPunch>false</isPunch>
  <isSound>false</isSound>
  <canMiss>false</canMiss>
</Move>