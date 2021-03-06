<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductAttributes extends AbstractMigration
{

    public function change(): void
    {
        $shopCats = $this->table('product_attributes', ['signed' => false]);
        $shopCats->addColumn('product_id', 'biginteger', ['signed' => false])
            ->addColumn('language_id', 'biginteger', ['signed' => false])
            ->addColumn('title', 'string', ['limit' => 55])
            ->addColumn('value', 'string', ['limit' => 55])
            ->addForeignKey('product_id', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('language_id', 'languages', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
