<Move>
  <name>Hyper Fang</name>
  <type>NORMAL</type>
  <PP>15</PP>
  <accuracy>90</accuracy>
  <speed>0</speed>
  <DT>PHYSICAL</DT>
  <effect>
    <require>
	</require>
    <effect>
	  <Damage>
	    <power>80</power>
      </Damage>
	  <Chance>
	    <chance>10</chance>
		<effect>
		  <SetFlag>
			<target>target</target>
			<flag>1</flag>
		  </SetFlag>
		</effect>
	  </Chance>
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