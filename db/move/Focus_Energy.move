<Move>
  <name>Focus Energy</name>
  <type>NORMAL</type>
  <PP>30</PP>
  <accuracy>0</accuracy>
  <speed>0</speed>
  <DT>NONE</DT>
  <effect>
    <require>
	</require>
    <effect>
	  <SetFlag>
	    <target>user</target>
		<flag>2</flag>
		<text>$target's critical hit ratio was raised!</text>
	  </SetFlag>
	  <SetCounter>
	    <target>user</target>
		<counter>2</counter>
		<type>modify</type>
		<n>2</n>
	  </SetCounter>
    </effect>
  </effect>
  <isContact>false</isContact>
  <isProtectable>false</isProtectable>
  <isReflectable>false</isReflectable>
  <isSnatchable>true</isSnatchable>
  <isMirrorable>false</isMirrorable>
  <isPunch>false</isPunch>
  <isSound>false</isSound>
  <canMiss>false</canMiss>
</Move>