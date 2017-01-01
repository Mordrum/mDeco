package com.mordrum.mdeco.object;

public enum DMPAncientBlockType {
   ancientBrickGray("stoneAncientBrickGray"),
   ancientBrickTan("stoneAncientBrickTan");

   public final String oreDictName;

   private DMPAncientBlockType(String oreDictName) {
      this.oreDictName = oreDictName;
   }
}
