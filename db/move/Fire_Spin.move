<Move>
  <name>Fire Spin</name>
  <type>FIRE</type>
  <PP>15</PP>
  <accuracy>85</accuracy>
  <speed>0</speed>
  <DT>SPECIAL</DT>
  <effect>
    <require>
	</require>
    <effect>
	  <Damage>
	    <power>35</power>
	  </Damage>
	  <SetFlag>
	    <target>user</target>
		<flag>6</flag>
	  </SetFlag>
	  <SetCounter>
	    <target>user</target>
		<counter>6</counter>
		<type>random</type>
		<min>2</min>
		<max>5</max>
	  </SetCounter>
    </effect>
  </effect>
  <isContact>true</isContact>
  <isProtectable>true</isProtectable>
  <isReflectable>false</isReflectable>
  <isSnatchable>false</isSnatchable>
  <isMirrorable>true</isMirrorable>
  <isPunch>false</isPunch>
  <isSound>false</isSound>
  <canMiss>true</canMiss>
</Move>