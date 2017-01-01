package com.mordrum.mdeco.json;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.core.DMPHelper;

import java.io.File;

public abstract class DMPJsonGenerator {
   public static void generateJsonFiles(String rootPath) {
      File outputDir = new File(rootPath + "json");
      if(outputDir.exists()) {
         DMPHelper.deleteOutputDirectory(outputDir);
         MDeco.instance.logOutput("Developer .json files deleted");
      }

   }

   private static void checkOutputDirectories(String rootPath) {
      File outputDir = new File(rootPath + "json");
      if(!outputDir.exists()) {
         try {
            outputDir.mkdir();
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      outputDir = new File(rootPath + "json/blockstates");
      if(!outputDir.exists()) {
         try {
            outputDir.mkdir();
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

      outputDir = new File(rootPath + "json/models");
      if(!outputDir.exists()) {
         try {
            outputDir.mkdir();
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      outputDir = new File(rootPath + "json/models/block");
      if(!outputDir.exists()) {
         try {
            outputDir.mkdir();
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

      outputDir = new File(rootPath + "json/models/item");
      if(!outputDir.exists()) {
         try {
            outputDir.mkdir();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }
}
