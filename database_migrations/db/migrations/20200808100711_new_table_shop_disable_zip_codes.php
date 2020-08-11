<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopDisableZipCodes extends AbstractMigration
{

    public function change(): void
    {
        $shopDisableZipCodes = $this->table('shop_disable_zip_codes', ['signed' => false]);
        $shopDisableZipCodes->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('zip', 'string', ['limit' => 12])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
