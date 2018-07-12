/*
 * Copyright 2013-2017 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.ramltopojo.extensions.jackson2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.squareup.javapoet.*;
import org.raml.ramltopojo.EcmaPattern;
import org.raml.ramltopojo.EventType;
import org.raml.ramltopojo.extensions.ObjectPluginContext;
import org.raml.ramltopojo.extensions.ObjectTypeHandlerPlugin;
import org.raml.v2.api.model.v10.datamodel.ObjectTypeDeclaration;
import org.raml.v2.api.model.v10.datamodel.TypeDeclaration;

import java.util.Map;

/**
 * Created by Jean-Philippe Belanger on 12/15/16. Just potential zeroes and ones
 */
public class JacksonBasicExtension extends ObjectTypeHandlerPlugin.Helper {

    public static final ParameterizedTypeName ADDITIONAL_PROPERTIES_TYPE = ParameterizedTypeName.get(
            Map.class, String.class,
            Object.class);

    @Override
    public TypeSpec.Builder classCreated(ObjectPluginContext objectPluginContext, ObjectTypeDeclaration obj, TypeSpec.Builder typeSpec, EventType eventType) {

        if (eventType != EventType.IMPLEMENTATION) {

            return typeSpec;
        }


        typeSpec.addAnnotation(AnnotationSpec.builder(JsonInclude.class)
                .addMember("value", "$T.$L", JsonInclude.Include.class, "NON_NULL").build());

        if (obj.discriminatorValue() != null) {

            typeSpec.addAnnotation(AnnotationSpec.builder(JsonTypeName.class)
                    .addMember("value", "$S", obj.discriminatorValue()).build());
        }


        AnnotationSpec.Builder builder = AnnotationSpec.builder(JsonPropertyOrder.class);
        for (TypeDeclaration declaration : obj.properties()) {

            if (EcmaPattern.isSlashedPattern(declaration.name())) {

                continue;
            }

            builder.addMember("value", "$S", declaration.name());
        }

        typeSpec.addAnnotation(builder.build());

        return typeSpec;

    }

    @Override
    public FieldSpec.Builder fieldBuilt(ObjectPluginContext objectPluginContext, TypeDeclaration declaration, FieldSpec.Builder fieldSpec, EventType eventType) {
        AnnotationSpec.Builder annotation = AnnotationSpec.builder(JsonProperty.class)
                .addMember("value", "$S", declaration.name());
        if (declaration.defaultValue() != null) {
            annotation.addMember("defaultValue", "$S", declaration.defaultValue());

        }
        return fieldSpec.addAnnotation(
                annotation.build());
    }

    @Override
    public MethodSpec.Builder getterBuilt(ObjectPluginContext objectPluginContext, TypeDeclaration declaration, MethodSpec.Builder methodSpec, EventType eventType) {

        AnnotationSpec.Builder annotation = AnnotationSpec.builder(JsonProperty.class)
                .addMember("value", "$S", declaration.name());
        if (declaration.defaultValue() != null) {
            annotation.addMember("defaultValue", "$S", declaration.defaultValue());
        }

        return methodSpec.addAnnotation(annotation.build());
    }

    @Override
    public MethodSpec.Builder setterBuilt(ObjectPluginContext objectPluginContext, TypeDeclaration declaration, MethodSpec.Builder methodSpec, EventType eventType) {
        AnnotationSpec.Builder annotation = AnnotationSpec.builder(JsonProperty.class)
                .addMember("value", "$S", declaration.name());
        if (declaration.defaultValue() != null) {
            annotation.addMember("defaultValue", "$S", declaration.defaultValue());
        }

        return methodSpec.addAnnotation(annotation.build());
    }
}
