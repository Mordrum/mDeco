package com.mordrum.mdeco.core;

public enum DMPConnectionsAll {
   NONE(11, 0, 0, "down=false,east=false,north=false,south=false,up=false,west=false"),
   D(0, 0, 0, "down=true,east=false,north=false,south=false,up=false,west=false"),
   E(0, 90, 270, "down=false,east=true,north=false,south=false,up=false,west=false"),
   N(0, 90, 180, "down=false,east=false,north=true,south=false,up=false,west=false"),
   S(0, 90, 0, "down=false,east=false,north=false,south=true,up=false,west=false"),
   U(0, 180, 0, "down=false,east=false,north=false,south=false,up=true,west=false"),
   W(0, 90, 90, "down=false,east=false,north=false,south=false,up=false,west=true"),
   DE(2, 0, 90, "down=true,east=true,north=false,south=false,up=false,west=false"),
   DN(2, 0, 0, "down=true,east=false,north=true,south=false,up=false,west=false"),
   DS(2, 0, 180, "down=true,east=false,north=false,south=true,up=false,west=false"),
   DU(1, 0, 0, "down=true,east=false,north=false,south=false,up=true,west=false"),
   DW(2, 0, 270, "down=true,east=false,north=false,south=false,up=false,west=true"),
   EN(3, 0, 90, "down=false,east=true,north=true,south=false,up=false,west=false"),
   ES(3, 0, 180, "down=false,east=true,north=false,south=true,up=false,west=false"),
   EU(2, 270, 90, "down=false,east=true,north=false,south=false,up=true,west=false"),
   EW(1, 90, 90, "down=false,east=true,north=false,south=false,up=false,west=true"),
   NS(1, 90, 0, "down=false,east=false,north=true,south=true,up=false,west=false"),
   NU(2, 180, 180, "down=false,east=false,north=true,south=false,up=true,west=false"),
   NW(3, 0, 0, "down=false,east=false,north=true,south=false,up=false,west=true"),
   SU(2, 180, 0, "down=false,east=false,north=false,south=true,up=true,west=false"),
   SW(3, 0, 270, "down=false,east=false,north=false,south=true,up=false,west=true"),
   UW(2, 180, 90, "down=false,east=false,north=false,south=false,up=true,west=true"),
   DEN(4, 0, 90, "down=true,east=true,north=true,south=false,up=false,west=false"),
   DES(4, 0, 180, "down=true,east=true,north=false,south=true,up=false,west=false"),
   DEU(5, 0, 90, "down=true,east=true,north=false,south=false,up=true,west=false"),
   DEW(5, 90, 90, "down=true,east=true,north=false,south=false,up=false,west=true"),
   DNS(5, 90, 0, "down=true,east=false,north=true,south=true,up=false,west=false"),
   DNU(5, 0, 0, "down=true,east=false,north=true,south=false,up=true,west=false"),
   DNW(4, 0, 0, "down=true,east=false,north=true,south=false,up=false,west=true"),
   DSU(5, 0, 180, "down=true,east=false,north=false,south=true,up=true,west=false"),
   DSW(4, 90, 0, "down=true,east=false,north=false,south=true,up=false,west=true"),
   DUW(5, 0, 270, "down=true,east=false,north=false,south=false,up=true,west=true"),
   ENS(6, 0, 90, "down=false,east=true,north=true,south=true,up=false,west=false"),
   ENU(4, 180, 180, "down=false,east=true,north=true,south=false,up=true,west=false"),
   ENW(6, 0, 0, "down=false,east=true,north=true,south=false,up=false,west=true"),
   ESU(4, 180, 270, "down=false,east=true,north=false,south=true,up=true,west=false"),
   ESW(6, 0, 180, "down=false,east=true,north=false,south=true,up=false,west=true"),
   EUW(6, 270, 0, "down=false,east=true,north=false,south=false,up=true,west=true"),
   NSU(5, 270, 0, "down=false,east=false,north=true,south=true,up=true,west=false"),
   NSW(6, 0, 270, "down=false,east=false,north=true,south=true,up=false,west=true"),
   NUW(4, 180, 90, "down=false,east=false,north=true,south=false,up=true,west=true"),
   SUW(4, 180, 0, "down=false,east=false,north=false,south=true,up=true,west=true"),
   DENS(7, 90, 180, "down=true,east=true,north=true,south=true,up=false,west=false"),
   DENU(7, 0, 90, "down=true,east=true,north=true,south=false,up=true,west=false"),
   DENW(7, 90, 90, "down=true,east=true,north=true,south=false,up=false,west=true"),
   DESU(7, 0, 180, "down=true,east=true,north=false,south=true,up=true,west=false"),
   DESW(7, 90, 270, "down=true,east=true,north=false,south=true,up=false,west=true"),
   DEUW(8, 0, 90, "down=true,east=true,north=false,south=false,up=true,west=true"),
   DNSU(8, 0, 0, "down=true,east=false,north=true,south=true,up=true,west=false"),
   DNSW(7, 90, 0, "down=true,east=false,north=true,south=true,up=false,west=true"),
   DNUW(7, 0, 0, "down=true,east=false,north=true,south=false,up=true,west=true"),
   DSUW(7, 0, 270, "down=true,east=false,north=false,south=true,up=true,west=true"),
   ENSU(7, 270, 180, "down=false,east=true,north=true,south=true,up=true,west=false"),
   ENSW(9, 0, 0, "down=false,east=true,north=true,south=true,up=false,west=true"),
   ENUW(7, 270, 90, "down=false,east=true,north=true,south=false,up=true,west=true"),
   ESUW(7, 270, 270, "down=false,east=true,north=false,south=true,up=true,west=true"),
   NSUW(7, 270, 0, "down=false,east=false,north=true,south=true,up=true,west=true"),
   DENSU(10, 0, 90, "down=true,east=true,north=true,south=true,up=true,west=false"),
   DENSW(10, 90, 0, "down=true,east=true,north=true,south=true,up=false,west=true"),
   DENUW(10, 0, 0, "down=true,east=true,north=true,south=false,up=true,west=true"),
   DESUW(10, 0, 180, "down=true,east=true,north=false,south=true,up=true,west=true"),
   DNSUW(10, 0, 270, "down=true,east=false,north=true,south=true,up=true,west=true"),
   ENSUW(10, 270, 0, "down=false,east=true,north=true,south=true,up=true,west=true"),
   DENSUW(11, 0, 0, "down=true,east=true,north=true,south=true,up=true,west=true");

   public final int modelID;
   public final int xRotation;
   public final int yRotation;
   public final String stateString;

   private DMPConnectionsAll(int modelID, int xRotation, int yRotation, String stateString) {
      this.modelID = modelID;
      this.xRotation = xRotation;
      this.yRotation = yRotation;
      this.stateString = stateString;
   }
}
