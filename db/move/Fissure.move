<Move>
  <name>Fissure</name>
  <type>GROUND</type>
  <PP>5</PP>
  <accuracy>100</accuracy>
  <speed>0</speed>
  <DT>PHYSICAL</DT>
  <effect>
    <require>
	  <OHKOCheck/>
	</require>
    <effect>
      <StatusEffect>
        <target>target</target>
		<status>Fainted</status>
      </StatusEffect>
    </effect>
  </effect>
  <isContact>false</isContact>
  <isProtectable>true</isProtectable>
  <isReflectable>false</isReflectable>
  <isSnatchable>false</isSnatchable>
  <isMirrorable>true</isMirrorable>
  <isPunch>false</isPunch>
  <isSound>false</isSound>
  <canMiss>false</canMiss>
</Move>